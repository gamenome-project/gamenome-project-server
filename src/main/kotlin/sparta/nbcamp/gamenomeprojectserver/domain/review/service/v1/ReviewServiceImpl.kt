package sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sparta.nbcamp.gamenomeprojectserver.domain.report.entity.v1.EntityType
import sparta.nbcamp.gamenomeprojectserver.domain.report.entity.v1.Report
import sparta.nbcamp.gamenomeprojectserver.domain.report.repository.v1.ReportRepository
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewCreateDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewReportDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewUpdateDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review
import sparta.nbcamp.gamenomeprojectserver.domain.review.repository.v1.ReviewRepository
import sparta.nbcamp.gamenomeprojectserver.domain.user.repository.UserRepository
import sparta.nbcamp.gamenomeprojectserver.exception.ModelNotFoundException

@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository,
    private val reportRepository: ReportRepository,
    private val userRepository: UserRepository
) : ReviewService {
    @Transactional
    override fun createReview(reviewCreateDTO: ReviewCreateDto): ReviewDto {
        val review = Review.fromDto(reviewCreateDTO)
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

    @Transactional
    override fun reportReview(reviewId: Long, reviewReportDTO: ReviewReportDto): ReviewDto {
        val foundReview = reviewRepository.findByIdOrNull(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        val user = userRepository.findByIdOrNull(reviewReportDTO.userId) ?: throw ModelNotFoundException(
            "User",
            reviewReportDTO.userId
        )
        val report = Report.fromDto(user, reviewId, EntityType.Review, reviewReportDTO)
        reportRepository.save(report)
        return ReviewDto.from(foundReview)
    }

    override fun getReviewReport(): List<ReviewDto> {
        val reportedReviews = reportRepository.findByEntityType(EntityType.Review)
        val reportedReviewIds = reportedReviews.map { it.entityId }.distinct()
        val foundReviews = reviewRepository.findAllById(reportedReviewIds)
        return foundReviews.map { ReviewDto.from(it) }
    }
}