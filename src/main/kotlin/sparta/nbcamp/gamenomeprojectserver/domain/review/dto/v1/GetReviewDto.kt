package sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review
import java.time.LocalDateTime

data class GetReviewDto(
    val gameName: String,
    val title: String,
    val description: String,
    val isDeleted: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
    val starScore: Float,
) {
    companion object {
        fun from(review: Review, score: Float): GetReviewDto {
            return GetReviewDto(
                review.gameName,
                review.title,
                review.description,
                review.isDeleted,
                review.createdAt,
                review.updatedAt,
                score
            )
        }
    }
}
