package sparta.nbcamp.gamenomeprojectserver.domain.follow.model.v1

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class FollowId(
    val user: Long = 0,
    val followingUserId: Long = 0,
): Serializable
