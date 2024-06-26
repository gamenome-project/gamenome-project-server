package sparta.nbcamp.gamenomeprojectserver.domain.follow.model.v1

import jakarta.persistence.*
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

@IdClass(FollowId::class)
@Entity
@Table(name = "follow")
class Follow(
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Id
    @Column(name = "following_user_id", nullable = false)
    val followingUserId: Long,
) {
    companion object {
        fun from(followingUserId: Long, user: User): Follow {
            return Follow(
                followingUserId = followingUserId,
                user = user
            )
        }
    }
}