package sparta.nbcamp.gamenomeprojectserver.domain.user.dto

data class UserDto(
    val id: Long,
    val nickname: String,
    val email: String,
    val isAdmin: Boolean,
)
