package sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1

import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.UserDto

interface UserService {
    fun signUp(): UserDto
    fun signIn(): UserDto
    fun getUserProfile(): UserDto
    fun updateProfile(): UserDto
    fun deactivateUser()
}