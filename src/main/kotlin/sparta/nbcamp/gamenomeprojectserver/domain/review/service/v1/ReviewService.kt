package sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewCreateDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewReportDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewUpdateDto

interface ReviewService {
    fun createReview(reviewCreateDTO: ReviewCreateDto): ReviewDto

    fun getReviewPage(pageable: Pageable): Page<ReviewDto>

    fun updateReview(reviewId: Long, reviewUpdateDTO: ReviewUpdateDto, token: String): ReviewDto

    fun deleteReview(reviewId: Long)

    fun getReview(reviewId: Long): ReviewDto

    fun reportReview(reviewId: Long, reviewReportDTO: ReviewReportDto): ReviewDto

    fun getReviewReport(): List<ReviewDto>
}