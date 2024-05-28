package sparta.nbcamp.gamenomeprojectserver.domain.user.model

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.SignUpDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.dto.UserUpdateProfileDto
import java.time.LocalDateTime

@Entity
@Table(name = "app_user")
class User(
    @Column(name = "email", unique = true, nullable = false)
    var email: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Embedded
    var profile: Profile,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: UserRole = UserRole.User,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    var updatedAt: LocalDateTime? = null,

    @Column(name = "last_signin_at", nullable = true)
    var lastSignInAt: LocalDateTime? = null,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun updateProfile(request: UserUpdateProfileDto) {
        this.email = request.email
        this.password = request.password
        this.profile = Profile(
            nickname = request.nickname,
            aboutSummary = request.aboutSummary,
            profileImageUrl = request.profileImageUrl
        )
    }

    fun checkUpdatePermission(user: User): Boolean {
        return this.id == user.id || user.role == UserRole.Admin
    }

    fun signIn() {
        this.lastSignInAt = LocalDateTime.now()
    }

    companion object {
        fun fromDto(request: SignUpDto): User {
            return User(
                email = request.email,
                password = request.password,
                profile = Profile(
                    nickname = request.nickname,
                    aboutSummary = request.aboutSummary,
                )
            )
        }
    }
}
