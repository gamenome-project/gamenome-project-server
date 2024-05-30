package sparta.nbcamp.gamenomeprojectserver.domain.follow.model.v1

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
class FollowId(
    var user: Long = 0,
    var followingUserId: Long = 0
):Serializable