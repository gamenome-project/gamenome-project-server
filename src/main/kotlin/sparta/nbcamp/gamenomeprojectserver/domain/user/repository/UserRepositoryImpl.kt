package sparta.nbcamp.gamenomeprojectserver.domain.user.repository

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

@Repository
class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository
) : UserRepository {
    override fun findAllById(ids: List<Long>): List<User> {
        return userJpaRepository.findAllById(ids)
    }

    override fun findByEmail(email: String): User? {
        return userJpaRepository.findByEmail(email)
    }

    override fun find(userId: Long): User? {
        return userJpaRepository.findByIdOrNull(userId)
    }

    override fun exists(userId: Long): Boolean {
        return userJpaRepository.existsById(userId)
    }

    override fun existsByNickname(nickname: String): Boolean {
        return userJpaRepository.existsByProfileNickname(nickname)
    }

    override fun save(user: User): User {
        return userJpaRepository.save(user)
    }

    override fun delete(user: User) {
        userJpaRepository.delete(user)
    }
}