package sparta.nbcamp.gamenomeprojectserver.domain.user.controller.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sparta.nbcamp.gamenomeprojectserver.domain.security.jwt.JwtResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.SignInDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.SignUpDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.UserDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.UserUpdateProfileDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1.UserService

@RequestMapping("api/v1")
@RestController
class UserController(
    val userService: UserService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpDto): ResponseEntity<UserDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(request))
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody request: SignInDto): ResponseEntity<JwtResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(request))
    }

    @PostMapping("/signout")
    fun signOut(): ResponseEntity<Unit> {
        // TODO: signOut Logic (세션은 Service 안가고 Controller에서 바로 끊었는데 JWT는 아직 불확실함)

        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/users/{userId}/profile")
    fun getProfile(@PathVariable("userId") userId: Long): ResponseEntity<UserDto> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserProfile(userId))
    }

    @PutMapping("/users/{userId}/profile")
    fun updateProfile(
        @PathVariable("userId") userId: Long,
        @RequestBody request: UserUpdateProfileDto
    ): ResponseEntity<UserDto> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateProfile(userId, request))
    }

    @DeleteMapping("/users/deactivate")
    fun deactivateUser(): ResponseEntity<Unit> {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.deactivateUser(userId))
    }

    @GetMapping("/users/checkNickname")
    fun checkNicknameDuplicate(@RequestParam nickname: String): ResponseEntity<Boolean> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.isNicknameDuplicate(nickname))
    }
}