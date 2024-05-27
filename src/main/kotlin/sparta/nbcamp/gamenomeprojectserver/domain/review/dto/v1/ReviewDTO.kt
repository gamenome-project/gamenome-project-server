package sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1

import java.time.LocalDateTime

data class ReviewDTO(
    val gameName: String,
    val title: String,
    val description: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
