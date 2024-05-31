package sparta.nbcamp.gamenomeprojectserver.domain.security.service

import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.security.jwt.JwtPlugin

@Service
class AuthServiceImpl(
    val jwtPlugin: JwtPlugin
): AuthService {
    override fun generateToken(userId: Long): String {
        return jwtPlugin.generateAccessToken("userId", userId)
    }

    override fun isValidToken(token: String): Boolean {
        return jwtPlugin.validateToken(token).isSuccess
    }

    override fun getUserIdFromToken(token: String): Long {
        return (jwtPlugin.validateToken(token).getOrNull()?.payload?.get("userId") as Int).toLong()
    }
}