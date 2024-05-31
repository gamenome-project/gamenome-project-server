package sparta.nbcamp.gamenomeprojectserver.domain.security.service

interface AuthService {
    fun generateToken(userId: Long): String
    fun isValidToken(token: String): Boolean
    fun getUserIdFromToken(token: String): Long
}