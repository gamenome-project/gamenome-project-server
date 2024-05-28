package sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1

import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewCreateDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewReportDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewUpdateDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.repository.v1.ReviewRepository

@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository
) : ReviewService {
    override fun createReview(reviewCreateDTO: ReviewCreateDto): ReviewDto {
        TODO("Not yet implemented")
    }

    override fun getReviewList(): List<ReviewDto> {
        TODO("Not yet implemented")
    }

    override fun updateReview(reviewId: Long, reviewUpdateDTO: ReviewUpdateDto): ReviewDto {
        TODO("Not yet implemented")
    }

    override fun deleteReview(reviewId: Long) {
        TODO("Not yet implemented")
    }

    override fun getReview(reviewId: Long): ReviewDto {
        TODO("Not yet implemented")
    }

    override fun reportReview(reviewId: Long, reviewReportDTO: ReviewReportDto): ReviewDto {
        TODO("Not yet implemented")
    }

    override fun getReviewReport(): List<ReviewDto> {
        TODO("Not yet implemented")
    }

}