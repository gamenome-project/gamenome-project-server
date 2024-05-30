package sparta.nbcamp.gamenomeprojectserver.domain.user.dto

data class SignInDto(
    override val email: String,
    override val password: String
) : EmailPasswordValidatableDto(email, password)
