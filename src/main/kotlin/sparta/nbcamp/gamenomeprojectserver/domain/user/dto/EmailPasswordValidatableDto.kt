package sparta.nbcamp.gamenomeprojectserver.domain.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

abstract class EmailPasswordValidatableDto(

    @field:NotBlank
    @field:Size(max = 50, message = "Email must be 50 characters or less")
    @field:Email(message = "Email must be a valid email address")
    open val email: String,

    @field:NotBlank
    @field:Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    open val password: String
)