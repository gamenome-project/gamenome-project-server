package sparta.nbcamp.gamenomeprojectserver.domain.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

interface UserJpaRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
    fun findByProfileNicknameAndProvider(nickname: String, provider: String): User?
    fun existsByProfileNickname(nickname: String): Boolean
}
