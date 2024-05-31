package sparta.nbcamp.gamenomeprojectserver.domain.user.oauth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class OAuthKakaoCreateDto(
    @field:NotBlank
    @field:Size(max = 50, message = "Email must be 50 characters or less")
    @field:Email(message = "Email must be a valid email address")
    open val email: String,

    @field:NotBlank
    @field:Size(max = 50, message = "About summary must be 50 characters or less")
    val aboutSummary: String?,

    val profileImageUrl: String?
)