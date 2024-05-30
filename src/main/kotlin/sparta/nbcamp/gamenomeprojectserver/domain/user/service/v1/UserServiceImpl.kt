package sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1

import jakarta.mail.Message
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import org.springframework.data.repository.findByIdOrNull
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sparta.nbcamp.gamenomeprojectserver.domain.common.utils.Secret
import sparta.nbcamp.gamenomeprojectserver.domain.security.jwt.JwtPlugin
import sparta.nbcamp.gamenomeprojectserver.domain.security.jwt.JwtResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.*
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User
import sparta.nbcamp.gamenomeprojectserver.domain.user.repository.UserRepository
import sparta.nbcamp.gamenomeprojectserver.exception.ModelNotFoundException
import java.util.*

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val jwtPlugin: JwtPlugin,
    val javaMailSender: JavaMailSender,
) : UserService {

    @Transactional
    override fun signUp(request: SignUpDto): UserDto {
        // TODO: 패스워드 암호화

        return UserDto.fromEntity(
            userRepository.save(
                User.fromDto(request)
            )
        )
    }

    @Transactional
    override fun signIn(request: SignInDto): JwtResponseDto {
        val user = userRepository.findByEmail(request.email)
            ?: throw IllegalStateException("User not found with email")

        // TODO: 패스워드 체크

        val accessToken =
            jwtPlugin.generateAccessToken("userId", user.id ?: throw RuntimeException("User id is invalid"))

        user.signIn()

        return JwtResponseDto(accessToken)
    }

    override fun getUserProfile(userId: Long): UserDto {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User not found", userId)

        return UserDto.fromEntity(user)
    }

    override fun getUserProfileList(userIds: List<Long>): List<UserDto> {
        return userRepository.findAllById(userIds).map { UserDto.fromEntity(it) }
    }

    @Transactional
    override fun updateProfile(userId: Long, request: UserUpdateProfileDto): UserDto {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User not found", userId)

//        if (!user.checkUpdatePermission())
        user.updateProfile(request)

        return UserDto.fromEntity(user)
    }

    @Transactional
    override fun deactivateUser(userId: Long) {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User not found", userId)

        userRepository.delete(user)
    }

    override fun isValidToken(token: String): Boolean {
        return jwtPlugin.validateToken(token).isSuccess
    }

    override fun getUserIdFromToken(token: String): Long {
        return (jwtPlugin.validateToken(token).getOrNull()?.payload?.get("userId") as Int).toLong()
    }

    override fun isNicknameDuplicate(nickname: String): Boolean {
        return userRepository.findByProfileNickname(nickname) != null
    }

    override fun sendMail(email: String): SendMailResponse {
        val code = UUID.randomUUID().toString().substring(0, 6);
        val mimeMessage = createMessage(code, email)
        javaMailSender.send(mimeMessage)
        return SendMailResponse(message = "메일 발송 완료")
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
