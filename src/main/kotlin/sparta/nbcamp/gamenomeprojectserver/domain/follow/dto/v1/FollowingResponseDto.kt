package sparta.nbcamp.gamenomeprojectserver.domain.follow.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

data class FollowingResponseDto(
    val userId: Long,
    val followingUserList: List<FollowingUser>
) {
    companion object {
        fun from(userId: Long, userList: List<User>): FollowingResponseDto {
            return FollowingResponseDto(
                userId = userId,
                followingUserList = userList.map { FollowingUser.from(it) }
            )
        }
    }
}
