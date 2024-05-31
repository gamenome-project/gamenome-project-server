package sparta.nbcamp.gamenomeprojectserver.domain.user.oauth

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.*
import org.springframework.stereotype.Controller
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import sparta.nbcamp.gamenomeprojectserver.domain.user.oauth.dto.OAuthKakaoCreateDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1.UserService
import java.security.SecureRandom


@Controller
class OAuthKakaoController(
    private val userService: UserService,
    private val OAuthKakaoConfig: OAuthKakaoConfig
) {
    @GetMapping("/oauth2/kakao/login")
    fun getKakaoToken(): String {
        val kakaoLoginUrl = "https://kauth.kakao.com/oauth/authorize" +
                "?client_id=${OAuthKakaoConfig.clientId}" +
                "&redirect_uri=${OAuthKakaoConfig.redirectUri}" +
                "&response_type=code"

        return "redirect:$kakaoLoginUrl"
    }

    @GetMapping("/oauth2/kakao/callback")
    @ResponseBody
    fun getKakaoRedirect(code: String): String {
        println("token: $code")

        val params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params["grant_type"] = "authorization_code"
        params["client_id"] = OAuthKakaoConfig.clientId
        params["redirect_uri"] = OAuthKakaoConfig.redirectUri
        params["code"] = code

        val headersForAccessToken = HttpHeaders()
        headersForAccessToken.contentType = MediaType.APPLICATION_FORM_URLENCODED

        val kakaoTokenRequest = HttpEntity(params, headersForAccessToken)

        val restTemplate = RestTemplate()

        val accessTokenResponse = restTemplate.exchange(
            "https://kauth.kakao.com/oauth/token",
            HttpMethod.POST,
            kakaoTokenRequest,
            OAuthKakaoToken::class.java
        )

        return accessTokenResponse.toString()
    }

    @PostMapping("/oauth2/kakao/user")
    @ResponseBody
    fun getKakaoUser(
        @RequestParam accessToken: String,
        @RequestBody request: OAuthKakaoCreateDto
    ): ResponseEntity<Any> {
        val header = HttpHeaders()
        header.contentType = MediaType.APPLICATION_FORM_URLENCODED
        header["Authorization"] = "Bearer $accessToken"

        val userRequest = HttpEntity(null, header)

        val restTemplate = RestTemplate()

        val userResponse = restTemplate.exchange(
            "https://kapi.kakao.com/v2/user/me",
            HttpMethod.GET,
            userRequest,
            String::class.java
        )

        val jsonNode = ObjectMapper().readTree(userResponse.body ?: "")

        val profileImageUrl = jsonNode["kakao_account"]["profile"]["profile_image_url"].asText("")
        val nickname = jsonNode["kakao_account"]["profile"]["nickname"].asText("")

        if (!userService.isNicknameDuplicate(nickname)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.signIn(nickname, "Kakao"))
        } else {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                    userService.signUp(
                        request = request,
                        password = generateRandomPassword(),
                        nickname = nickname,
                        profileImageUrl = profileImageUrl
                    )
                )
        }
    }

    private fun generateRandomPassword(length: Int = 12): String {
        val charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+"
        val secureRandom = SecureRandom()
        return (1..length)
            .map { secureRandom.nextInt(charPool.length) }
            .map(charPool::get)
            .joinToString("")
    }
}