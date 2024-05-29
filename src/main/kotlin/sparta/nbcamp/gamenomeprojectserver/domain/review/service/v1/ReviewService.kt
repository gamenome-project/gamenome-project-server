package sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1

import org.springframework.data.domain.Pageable
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewCreateDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewReportDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewUpdateDto

interface ReviewService {
    fun createReview(reviewCreateDTO: ReviewCreateDto): ReviewDto

    fun getReviewList(pageable: Pageable): List<ReviewDto>

    fun updateReview(reviewId: Long, reviewUpdateDTO: ReviewUpdateDto): ReviewDto

    fun deleteReview(reviewId: Long)

    fun getReview(reviewId: Long): ReviewDto

    fun reportReview(reviewId: Long, reviewReportDTO: ReviewReportDto): ReviewDto

    fun getReviewReport(): List<ReviewDto>
}