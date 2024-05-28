package sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1

import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewCreateDTO
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewDTO
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewReportDTO
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewUpdateDTO

interface ReviewService {
    fun createReview(reviewCreateDTO: ReviewCreateDTO): ReviewDTO

    fun getReviewList(): List<ReviewDTO>

    fun updateReview(reviewId: Long, reviewUpdateDTO: ReviewUpdateDTO): ReviewDTO

    fun deleteReview(reviewId: Long)

    fun getReview(reviewId: Long): ReviewDTO

    fun reportReview(reviewId: Long, reviewReportDTO: ReviewReportDTO): ReviewDTO

    fun getReviewReport(): List<ReviewDTO>
}