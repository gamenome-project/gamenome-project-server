package sparta.nbcamp.gamenomeprojectserver.domain.follow.service.v1

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.follow.dto.v1.FollowingRequestDto
import sparta.nbcamp.gamenomeprojectserver.domain.follow.dto.v1.FollowingResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.follow.model.v1.Follow
import sparta.nbcamp.gamenomeprojectserver.domain.follow.repository.v1.FollowRepository
import sparta.nbcamp.gamenomeprojectserver.domain.user.repository.UserRepository
import sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1.UserService
import sparta.nbcamp.gamenomeprojectserver.exception.DuplicatedException
import sparta.nbcamp.gamenomeprojectserver.exception.ModelNotFoundException


@Service
class FollowService(
    val followRepository: FollowRepository,
    val userService: UserService,
    val userRepository: UserRepository
) {

    fun followUser(followingRequestDto: FollowingRequestDto, token: String){

        val userId = userService.getUserIdFromToken(token)
        val user = userRepository.findByIdOrNull(userId)?: throw ModelNotFoundException("User", userId)
        val follow = followRepository.findByFollowingUserId(followingRequestDto.followingUserId)

        if(follow != null){
            followRepository.delete(follow)
        }else{
            if(userId == followingRequestDto.followingUserId){
                throw DuplicatedException("본인은 팔로우 할 수 없습니다")
            }
            val follower = Follow.from(followingRequestDto, user)
            followRepository.save(follower)
        }
    }

    fun getFollowingUserList(token: String): List<FollowingResponseDto>? {

        val userId = userService.getUserIdFromToken(token)

        val result = followRepository.findAllByUserId(userId)

        val allUser = userRepository.findAllById(result.map { it.followingUserId })


        return result.map { FollowingResponseDto.from(it, allUser) }
    }

}