package sparta.nbcamp.gamenomeprojectserver.domain.reaction.model.v1

import jakarta.persistence.Embeddable
import java.io.Serializable


@Embeddable
class ReactionId(
    val user: Long = 0,
    val comment: Long = 0,
): Serializable