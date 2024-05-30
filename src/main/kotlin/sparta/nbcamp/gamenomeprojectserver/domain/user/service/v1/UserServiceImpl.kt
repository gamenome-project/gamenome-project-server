package sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sparta.nbcamp.gamenomeprojectserver.domain.security.jwt.JwtResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.security.service.AuthService
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.SignInDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.SignUpDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.UserDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.UserUpdateProfileDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User
import sparta.nbcamp.gamenomeprojectserver.domain.user.repository.UserRepository
import sparta.nbcamp.gamenomeprojectserver.exception.ModelNotFoundException

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val authService: AuthService,
    val passwordEncoder: PasswordEncoder
) : UserService {

    @Transactional
    override fun signUp(request: SignUpDto): UserDto {
        val encryptedPassword = passwordEncoder.encode(request.password)

        return UserDto.fromEntity(
            userRepository.save(
                User.fromDto(request.copy(password = encryptedPassword))
            )
        )
    }

    @Transactional
    override fun signIn(request: SignInDto): JwtResponseDto {
        val user = userRepository.findByEmail(request.email)
            ?: throw IllegalStateException("User not found with email")

        if (!passwordEncoder.matches(request.password, user.password)) throw IllegalStateException("Password is incorrect")

        val accessToken = authService.generateToken(user.id!!)

        user.signIn()

        return JwtResponseDto(accessToken)
    }

    override fun getUserProfile(userId: Long): UserDto {
        val user = userRepository.find(userId) ?: throw ModelNotFoundException("User not found", userId)

        return UserDto.fromEntity(user)
    }

    @Transactional
    override fun updateProfile(userId: Long, request: UserUpdateProfileDto): UserDto {
        val user = userRepository.find(userId) ?: throw ModelNotFoundException("User not found", userId)

        user.updateProfile(request)

        return UserDto.fromEntity(user)
    }

    @Transactional
    override fun deactivateUser(userId: Long) {
        val user = userRepository.find(userId) ?: throw ModelNotFoundException("User not found", userId)

        userRepository.delete(user)
    }
}