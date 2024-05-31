package sparta.nbcamp.gamenomeprojectserver.domain.user.oauth

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.client.RestTemplate


@Controller
class OAuthKakaoController(
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

    @GetMapping("/oauth2/kakao/user")
    @ResponseBody
    fun getKakaoUser(@RequestParam accessToken: String): String {
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

        val responseBody: String = userResponse.body ?: ""
        val objectMapper = ObjectMapper()
        val jsonNode = objectMapper.readTree(responseBody)

        val id = jsonNode["id"].asLong()
        val profileImageUrl = jsonNode["kakao_account"]["profile"]["profile_image_url"].asText("")
        val nickname = jsonNode["kakao_account"]["profile"]["nickname"].asText("")

        return "id: $id, profileImageUrl: $profileImageUrl, nickname: $nickname"
    }
}