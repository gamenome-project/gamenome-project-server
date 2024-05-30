package sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1

import sparta.nbcamp.gamenomeprojectserver.domain.security.jwt.JwtResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.*

interface UserService {
    fun signUp(request: SignUpDto): UserDto
    fun signIn(request: SignInDto): JwtResponseDto
    fun getUserProfile(userId: Long): UserDto
    fun getUserProfileList(userIds: List<Long>): List<UserDto>
    fun updateProfile(userId: Long, request: UserUpdateProfileDto): UserDto
    fun deactivateUser(userId: Long)
    fun isValidToken(token: String): Boolean
    fun getUserIdFromToken(token: String): Long
    fun isNicknameDuplicate(nickname: String): Boolean
    fun sendMail(email : String): SendMailResponse
}