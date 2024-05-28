package sparta.nbcamp.gamenomeprojectserver.domain.user.dto

data class UserUpdateProfileDto(
    val email: String,
    val password: String,
    val nickname: String,
    val aboutSummary: String,
    val profileImageUrl: String?
)
