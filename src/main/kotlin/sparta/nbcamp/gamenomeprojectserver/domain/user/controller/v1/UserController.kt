package sparta.nbcamp.gamenomeprojectserver.domain.user.controller.v1

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import sparta.nbcamp.gamenomeprojectserver.domain.s3.service.v1.S3Service
import sparta.nbcamp.gamenomeprojectserver.domain.security.jwt.JwtResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.security.service.AuthService
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.*
import sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1.UserService

@RequestMapping("api/v1")
@RestController
class UserController(
    val userService: UserService,
    val authService: AuthService,
    val s3Service: S3Service,
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpDto): ResponseEntity<UserDto> {
        if (request.password != request.confirmPassword) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(request))
    }

    @PostMapping("/signin")
    fun signIn(@RequestBody request: SignInDto): ResponseEntity<JwtResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.signIn(request))
    }

    @PostMapping("/signout")
    fun signOut(): ResponseEntity<Unit> {
        // TODO: 로그아웃 로직에 대해서는 이슈 #82 참고

        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @GetMapping("/users/{userId}/profile")
    fun getProfile(@PathVariable("userId") userId: Long): ResponseEntity<UserDto> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserProfile(userId))
    }

    @PutMapping("/users/{userId}/profile")
    fun updateProfile(
        @PathVariable("userId") userId: Long,
        @RequestPart request: UserUpdateProfileDto,
        @RequestPart("profileImage", required = false) profileImage: MultipartFile?
    ): ResponseEntity<UserDto> {
        if (request.password != request.confirmPassword) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        val uploadImage = profileImage.let { s3Service.uploadImage(it!!) }
        val uploadImageUrl = uploadImage.let { s3Service.getImageUrl(it) }

        return ResponseEntity.status(HttpStatus.OK).body(userService.updateProfile(userId, request, uploadImageUrl))
    }

    @DeleteMapping("/users/deactivate")
    fun deactivateUser(request: HttpServletRequest): ResponseEntity<Unit> {
        val authorization =
            request.getHeader("Authorization") ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.deactivateUser(authorization))
    }

    @GetMapping("/token/checkUserId")
    fun checkUserId(
        request: HttpServletRequest
    ): ResponseEntity<Long> {
        val authorization =
            request.getHeader("Authorization") ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        return ResponseEntity.status(HttpStatus.OK).body(authService.getUserIdFromToken(authorization))
    }

    @GetMapping("/users/checkNickname")
    fun checkNicknameDuplicate(@RequestParam nickname: String): ResponseEntity<Boolean> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.isNicknameDuplicate(nickname))
    }

    @PostMapping("/users/email")
    fun sendMail(
        @RequestParam email: String
    ): ResponseEntity<SendMailResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.sendMail(email))
    }

    @PostMapping("/users/email/check")
    fun checkCertification(
        @RequestParam email: String,
        @RequestParam code: String
    ): ResponseEntity<SendMailResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.checkCertification(email, code))
    }
}