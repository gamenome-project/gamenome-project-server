package sparta.nbcamp.gamenomeprojectserver.domain.user.dto

data class SignUpDto (
    val email: String,
    val password: String,
    val nickname: String,
    val aboutSummary: String?,
)
