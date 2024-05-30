package sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1

import sparta.nbcamp.gamenomeprojectserver.domain.security.jwt.JwtResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.SignInDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.SignUpDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.UserDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.UserUpdateProfileDto

interface UserService {
    fun signUp(request: SignUpDto): UserDto
    fun signIn(request: SignInDto): JwtResponseDto
    fun getUserProfile(userId: Long): UserDto
    fun updateProfile(userId: Long, request: UserUpdateProfileDto): UserDto
    fun deactivateUser(userId: Long)
}