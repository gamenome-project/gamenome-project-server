package sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.*

interface ReviewService {
    fun createReview(reviewCreateDTO: ReviewCreateDto): ReviewDto

    fun getReviewPage(pageable: Pageable): Page<ReviewDto>

    fun updateReview(reviewId: Long, reviewUpdateDTO: ReviewUpdateDto, token: String): ReviewDto

    fun deleteReview(reviewId: Long)

    fun getReview(reviewId: Long): GetReviewDto

    fun reportReview(reviewId: Long, reviewReportDTO: ReviewReportDto): ReviewDto

    fun getReviewReport(): List<ReviewDto>

    fun getFollowingReviewPage(token: String, pageable: Pageable): Page<ReviewDto>
}