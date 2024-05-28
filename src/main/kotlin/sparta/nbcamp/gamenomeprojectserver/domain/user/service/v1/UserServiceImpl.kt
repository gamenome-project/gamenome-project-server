package sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1

import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.UserDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.repository.UserRepository

@Service
class UserServiceImpl(
    val userRepository: UserRepository
) : UserService {
    override fun signUp(): UserDto {
        TODO("Not yet implemented")
    }

    override fun signIn(): UserDto {
        TODO("Not yet implemented")
    }

    override fun getUserProfile(): UserDto {
        TODO("Not yet implemented")
    }

    override fun updateProfile(): UserDto {
        TODO("Not yet implemented")
    }

    override fun deactivateUser() {
        TODO("Not yet implemented")
    }
}