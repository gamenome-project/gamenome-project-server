package sparta.nbcamp.gamenomeprojectserver.domain.user.repository

import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

interface UserRepository {
    fun findAllById(ids: List<Long>): List<User>
    fun findByEmail(email: String): User?
    fun find(userId: Long): User?
    fun exists(userId: Long): Boolean
    fun existsByNickname(nickname: String): Boolean
    fun save(user: User): User
    fun delete(user: User)
}