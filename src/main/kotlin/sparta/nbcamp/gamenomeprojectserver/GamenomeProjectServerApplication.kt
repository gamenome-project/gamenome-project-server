package sparta.nbcamp.gamenomeprojectserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import sparta.nbcamp.gamenomeprojectserver.domain.security.jwt.JwtProperties

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class GamenomeProjectServerApplication

fun main(args: Array<String>) {
    runApplication<GamenomeProjectServerApplication>(*args)
}
