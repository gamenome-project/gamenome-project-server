package sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1

import sparta.nbcamp.gamenomeprojectserver.domain.security.jwt.JwtResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.*

interface UserService {
    fun signUp(request: SignUpDto): UserDto
    fun signIn(request: SignInDto): JwtResponseDto
    fun getUserProfile(userId: Long): UserDto
    fun updateProfile(userId: Long, request: UserUpdateProfileDto): UserDto
    fun deactivateUser(token: String)
    fun isNicknameDuplicate(nickname: String): Boolean
    fun sendMail(email : String): SendMailResponse
    fun checkCertification(email: String, code: String): SendMailResponse
}