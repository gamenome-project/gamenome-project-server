package sparta.nbcamp.gamenomeprojectserver.domain.starScore.model.v1

import jakarta.persistence.Embeddable
import java.io.Serializable

@Embeddable
data class StarScoreId(
    val user: Long = 0,
    val review: Long = 0,
    val comment: Long = 0,
):Serializable