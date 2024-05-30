package sparta.nbcamp.gamenomeprojectserver.domain.follow.dto.v1

data class FollowingResponseDto(
    val followingUser: FollowingUser
){
    companion object {
        fun from(followingUser: FollowingUser): FollowingResponseDto{
            return FollowingResponseDto(
                followingUser = followingUser
            )
        }
    }
}
