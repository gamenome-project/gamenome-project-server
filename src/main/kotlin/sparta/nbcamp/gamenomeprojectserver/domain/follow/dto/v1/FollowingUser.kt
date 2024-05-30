package sparta.nbcamp.gamenomeprojectserver.domain.follow.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

data class FollowingUser(
    var followingUserId: Long,
    val followingUserEmail: String,
    val followingUserNickName : String
){
    companion object{
        fun from(user: User): FollowingUser{
            return FollowingUser(
                followingUserId = user.id!!,
                followingUserEmail = user.email,
                followingUserNickName = user.profile.nickname
            )
        }
    }
}
