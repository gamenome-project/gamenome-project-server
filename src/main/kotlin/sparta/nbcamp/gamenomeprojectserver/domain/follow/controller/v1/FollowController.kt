package sparta.nbcamp.gamenomeprojectserver.domain.follow.controller.v1

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sparta.nbcamp.gamenomeprojectserver.domain.follow.dto.v1.FollowingRequestDto
import sparta.nbcamp.gamenomeprojectserver.domain.follow.dto.v1.FollowingResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.follow.service.v1.FollowService

@RestController
@RequestMapping(value = ["/api/v1/users/follows"])
class FollowController(
    private val followService: FollowService
){

    @PostMapping
    fun followUser(
        request : HttpServletRequest,
        @RequestBody followingRequestDto: FollowingRequestDto
    ): ResponseEntity<Unit>{

        val token = request.getHeader("Authorization") ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        followService.followUser(followingRequestDto, token)

        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping
    fun getFollowingUserList(
        request : HttpServletRequest
    ):ResponseEntity<List<FollowingResponseDto>>{

        val token = request.getHeader("Authorization") ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        return ResponseEntity.status(HttpStatus.OK).body(followService.getFollowingUserList(token))
    }

}
