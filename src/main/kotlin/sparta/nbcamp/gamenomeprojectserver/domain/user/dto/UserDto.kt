package sparta.nbcamp.gamenomeprojectserver.domain.user.dto

data class UserDto(
    val id: Long,
    val email: String,
    val nickname: String,
    val role: String,
    val aboutSummary: String,
    val profileImageUrl: String?
)
