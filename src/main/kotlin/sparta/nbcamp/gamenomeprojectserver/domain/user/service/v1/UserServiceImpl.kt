package sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1

import jakarta.mail.Message
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sparta.nbcamp.gamenomeprojectserver.domain.common.utils.RedisUtils
import sparta.nbcamp.gamenomeprojectserver.domain.common.utils.Secret
import sparta.nbcamp.gamenomeprojectserver.domain.security.jwt.JwtResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.security.service.AuthService
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.*
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User
import sparta.nbcamp.gamenomeprojectserver.domain.user.repository.UserRepository
import sparta.nbcamp.gamenomeprojectserver.exception.ModelNotFoundException
import java.util.*

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val authService: AuthService,
    val passwordEncoder: PasswordEncoder,
    val javaMailSender: JavaMailSender,
    val redisUtils: RedisUtils,
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
    override fun updateProfile(userId: Long, request: UserUpdateProfileDto, imageUrl: String?): UserDto {
        val user = userRepository.find(userId) ?: throw ModelNotFoundException("User not found", userId)

        user.updateProfile(request, imageUrl)

        return UserDto.fromEntity(user)
    }

    @Transactional
    override fun deactivateUser(token: String) {
        val userId = authService.getUserIdFromToken(token)
        val user = userRepository.find(userId) ?: throw ModelNotFoundException("User not found", userId)

        userRepository.delete(user)
    }

    override fun isNicknameDuplicate(nickname: String): Boolean {
        return !userRepository.existsByNickname(nickname)
    }

    override fun sendMail(email: String): SendMailResponse {
        val code = UUID.randomUUID().toString().substring(0, 6)
        val mimeMessage = createMessage(code, email)
        javaMailSender.send(mimeMessage)
        redisUtils.setDataExpire(email, code)
        return SendMailResponse(message = "메일 발송 완료")
    }

    override fun checkCertification(email: String, code: String): SendMailResponse {
        val storedCode = redisUtils.getData(email)?.trim() ?: "null code"
        val trimmedCode = code.trim()

        if (trimmedCode == storedCode) {
            redisUtils.deleteData(email)
            return SendMailResponse(message = "이메일 인증 성공")
        } else {
            return SendMailResponse(message = "인증번호가 잘못되었거나 인증 시간이 초과되었습니다. 다시 확인해주세요.")
        }
    }

    private fun createMessage(code: String, email: String): MimeMessage {
        val message: MimeMessage = javaMailSender.createMimeMessage()
        message.addRecipients(Message.RecipientType.TO, email)
        message.subject = "gamenome 프로젝트 인증 번호입니다."
        message.setText("이메일 인증코드: $code")
        message.setFrom(InternetAddress(Secret.RECIPIENT))
        return message
        
    }
}