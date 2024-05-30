package sparta.nbcamp.gamenomeprojectserver.domain.user.repository

import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

interface UserRepository {
    fun findByProfileNickname(nickname: String): User?
    fun findByEmail(email: String): User?
    fun find(userId: Long): User?
    fun exists(userId: Long): Boolean
    fun save(user: User): User
    fun delete(user: User)
}