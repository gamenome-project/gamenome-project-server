package sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewCreateDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewReportDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewUpdateDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review
import sparta.nbcamp.gamenomeprojectserver.domain.review.repository.v1.ReviewRepository
import sparta.nbcamp.gamenomeprojectserver.exception.ModelNotFoundException

@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository
) : ReviewService {
    @Transactional
    override fun createReview(reviewCreateDTO: ReviewCreateDto): ReviewDto {
        val review = Review(reviewCreateDTO.gameName, reviewCreateDTO.title, reviewCreateDTO.description)
        return ReviewDto.from(reviewRepository.save(review))
    }

    override fun getReviewList(): List<ReviewDto> {
        val foundAllReview = reviewRepository.findAll()
        return foundAllReview.map { ReviewDto.from(it) }
    }
    @Transactional
    override fun updateReview(reviewId: Long, reviewUpdateDTO: ReviewUpdateDto): ReviewDto {
        val foundReview = reviewRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        foundReview.updateReviewField(reviewUpdateDTO)
        return ReviewDto.from(foundReview)
    }

    override fun deleteReview(reviewId: Long) {
        reviewRepository.deleteById(reviewId)
    }

    override fun getReview(reviewId: Long): ReviewDto {
        val foundReview = reviewRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        return ReviewDto.from(foundReview)
    }

    override fun reportReview(reviewId: Long, reviewReportDTO: ReviewReportDto): ReviewDto {
        TODO("Not yet implemented")
    }

    override fun getReviewReport(): List<ReviewDto> {
        TODO("Not yet implemented")
    }

}