package sparta.nbcamp.gamenomeprojectserver.domain.user.controller.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sparta.nbcamp.gamenomeprojectserver.domain.ApiV1MappingConfig
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.SignInDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.UserDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.SignUpDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.UserUpdateProfileDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1.UserService

@RestController
class UserController(
    val userService: UserService
) : ApiV1MappingConfig() {
    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpDto): ResponseEntity<UserDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp())
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody request: SignInDto): ResponseEntity<UserDto> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn())
    }

    @PostMapping("/signout")
    fun signOut(): ResponseEntity<Unit> {
        // TODO: signOut Logic

        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/users/{userId}/profile")
    fun getProfile(@PathVariable("userId") userId: Long): ResponseEntity<UserDto> {
        return ResponseEntity.ok().body(userService.getUserProfile())
    }

    @PutMapping("/users/{userId}/profile")
    fun updateProfile(
        @PathVariable("userId") userId: Long,
        @RequestBody request: UserUpdateProfileDto
    ): ResponseEntity<UserDto> {
        return ResponseEntity.ok().body(userService.updateProfile())
    }

    @DeleteMapping("/users/deactivate")
    fun deactivateUser(): ResponseEntity<Unit> {
        return ResponseEntity.ok().body(userService.deactivateUser())
    }
}