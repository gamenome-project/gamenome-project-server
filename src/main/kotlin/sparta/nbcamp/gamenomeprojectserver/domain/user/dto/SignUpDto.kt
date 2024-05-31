package sparta.nbcamp.gamenomeprojectserver.domain.user.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class SignUpDto (
    override val email: String,
    override val password: String,

    val confirmPassword: String,

    @field:NotBlank
    @field:Size(min = 2, max = 20, message = "Nickname must be between 2 and 20 characters")
    val nickname: String,

    @field:NotBlank
    @field:Size(max = 50, message = "About summary must be 50 characters or less")
    val aboutSummary: String?,

    val profileImageUrl: String?
) : EmailPasswordValidatableDto(email, password)
