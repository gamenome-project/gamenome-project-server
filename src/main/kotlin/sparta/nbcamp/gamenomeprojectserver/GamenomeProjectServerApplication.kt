package sparta.nbcamp.gamenomeprojectserver

import org.apache.catalina.Context
import org.apache.catalina.connector.Connector
import org.apache.tomcat.util.descriptor.web.SecurityCollection
import org.apache.tomcat.util.descriptor.web.SecurityConstraint
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.servlet.server.ServletWebServerFactory
import org.springframework.context.annotation.Bean
import sparta.nbcamp.gamenomeprojectserver.domain.security.jwt.JwtProperties

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties::class)
class GamenomeProjectServerApplication{

    @Bean
    fun servletContainer(): ServletWebServerFactory {

        val tomcat = object : TomcatServletWebServerFactory() {
            override fun postProcessContext(context: Context) {
                val securityConstraint = SecurityConstraint()
                securityConstraint.userConstraint = "CONFIDENTIAL"
                val collection = SecurityCollection()
                collection.addPattern("/*")
                securityConstraint.addCollection(collection)
                context.addConstraint(securityConstraint)
            }
        }

        tomcat.addAdditionalTomcatConnectors(createStandardConnector())
        return tomcat
    }

    fun createStandardConnector(): Connector {

        val connector = Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL)
        connector.scheme = "http"
        connector.port = 8080
        connector.secure = false
        connector.redirectPort = 8443

        return connector
    }
}

fun main(args: Array<String>) {
    runApplication<GamenomeProjectServerApplication>(*args)
}
