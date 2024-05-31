package sparta.nbcamp.gamenomeprojectserver.domain.user.dto

import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

data class UserDto(
    val id: Long,
    val email: String,
    val nickname: String,
    val role: String,
    val aboutSummary: String?,
    val profileImageUrl: String?
) {
    companion object {
        fun fromEntity(user: User): UserDto {
            return UserDto(
                id = user.id!!,
                email = user.email,
                nickname = user.profile.nickname,
                role = user.role.name,
                aboutSummary = user.profile.aboutSummary,
                profileImageUrl = user.profile.profileImageUrl
            )
        }
    }
}
