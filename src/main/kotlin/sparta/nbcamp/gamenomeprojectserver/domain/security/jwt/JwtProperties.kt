package sparta.nbcamp.gamenomeprojectserver.domain.security.jwt

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "jwt")
class JwtProperties {
    lateinit var secret: String
    val issuer = "sparta.nbcamp.gamenomeprojectserver"
    val expirationHours: Long = 168
}