package sparta.nbcamp.gamenomeprojectserver.domain.user.controller.v1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class OAuthController {
    @GetMapping("/users/kakao/callback")
    fun kakaoRedirect(code: String): String {
        return "인증 코드 : $code"
    }
}