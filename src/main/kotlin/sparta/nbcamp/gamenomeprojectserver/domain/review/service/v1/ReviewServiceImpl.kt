package sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1

import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewCreateDTO
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewDTO
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewReportDTO
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewUpdateDTO
import sparta.nbcamp.gamenomeprojectserver.domain.review.repository.v1.ReviewRepository

@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository
) : ReviewService {
    override fun createReview(reviewCreateDTO: ReviewCreateDTO): ReviewDTO {
        TODO("Not yet implemented")
    }

    override fun getReviewList(): List<ReviewDTO> {
        TODO("Not yet implemented")
    }

    override fun updateReview(reviewId: Long, reviewUpdateDTO: ReviewUpdateDTO): ReviewDTO {
        TODO("Not yet implemented")
    }

    override fun deleteReview(reviewId: Long) {
        TODO("Not yet implemented")
    }

    override fun getReview(reviewId: Long): ReviewDTO {
        TODO("Not yet implemented")
    }

    override fun reportReview(reviewId: Long, reviewReportDTO: ReviewReportDTO): ReviewDTO {
        TODO("Not yet implemented")
    }

    override fun getReviewReport(): List<ReviewDTO> {
        TODO("Not yet implemented")
    }

}