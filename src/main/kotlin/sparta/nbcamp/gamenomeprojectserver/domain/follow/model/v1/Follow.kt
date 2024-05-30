package sparta.nbcamp.gamenomeprojectserver.domain.follow.model.v1

import jakarta.persistence.*
import sparta.nbcamp.gamenomeprojectserver.domain.follow.dto.v1.FollowingRequestDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

@Entity
@Table(name = "follow")
class Follow(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "following_user_id", nullable = false)
    val followingUserId: Long,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null


    companion object {
        fun from(followingRequestDto: FollowingRequestDto, user: User): Follow {
            return Follow(
                followingUserId = followingRequestDto.followingUserId,
                user = user
            )
        }
    }
}