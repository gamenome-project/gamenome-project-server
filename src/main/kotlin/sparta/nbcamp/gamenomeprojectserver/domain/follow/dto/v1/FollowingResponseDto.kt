package sparta.nbcamp.gamenomeprojectserver.domain.follow.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.follow.model.v1.Follow

data class FollowingResponseDto(
    var followingUserId: Long,
    val followingUserEmail: String,
    val followingUserNickName : String
){
    companion object{
        fun from(follow: Follow): FollowingResponseDto{
            return FollowingResponseDto(
                followingUserId = follow.followingUserId,
                followingUserEmail = follow.user.email,
                followingUserNickName = follow.user.profile.nickname
            )
        }
    }
}
