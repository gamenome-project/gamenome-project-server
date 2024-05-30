package sparta.nbcamp.gamenomeprojectserver.domain.follow.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.follow.model.v1.Follow
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

data class FollowingUser(
    var followingUserId: Long,
    val followingUserEmail: String,
    val followingUserNickName : String
){
    companion object{
        fun from(follow: Follow, user: List<User>): FollowingUser{
            return FollowingUser(
                followingUserId = follow.followingUserId,
                followingUserEmail = user.filter { it.id == follow.followingUserId }.map { it.email }.toString().trim('[',']'),
                followingUserNickName = user.filter { it.id == follow.followingUserId }.map { it.profile.nickname }.toString().trim('[',']')
            )
        }
    }
}
