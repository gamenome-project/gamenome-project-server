package sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sparta.nbcamp.gamenomeprojectserver.domain.report.model.v1.EntityType
import sparta.nbcamp.gamenomeprojectserver.domain.report.service.ReportService
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
    private val userRepository: UserRepository,

    private val reportService: ReportService,
) : ReviewService {
    @Transactional
    override fun createReview(reviewCreateDTO: ReviewCreateDto): ReviewDto {
        val review = Review.fromDto(reviewCreateDTO)
        return ReviewDto.from(reviewRepository.save(review))
    }

    override fun getReviewPage(pageable: Pageable): Page<ReviewDto> {
        val foundAllReview = reviewRepository.findAll(pageable)
        return foundAllReview.map { ReviewDto.from(it) }
    }

    @Transactional
    override fun updateReview(reviewId: Long, reviewUpdateDTO: ReviewUpdateDto): ReviewDto {
        val foundReview = reviewRepository.find(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        foundReview.updateReviewField(reviewUpdateDTO)
        return ReviewDto.from(foundReview)
    }

    @Transactional
    override fun deleteReview(reviewId: Long) {
        reviewRepository.deleteById(reviewId)
    }

    override fun getReview(reviewId: Long): ReviewDto {
        val foundReview = reviewRepository.find(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        return ReviewDto.from(foundReview)
    }

    @Transactional
    override fun reportReview(reviewId: Long, reviewReportDTO: ReviewReportDto): ReviewDto {
        val foundReview = reviewRepository.find(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        val user = userRepository.find(reviewReportDTO.userId) ?: throw ModelNotFoundException(
            "User",
            reviewReportDTO.userId
        )
        reportService.createReport(user, reviewId, EntityType.Review, reviewReportDTO)
        return ReviewDto.from(foundReview)
    }

    override fun getReviewReport(): List<ReviewDto> {
        val reportedReviews = reportService.getReportsByEntityType(EntityType.Review)
        val reportedReviewIds = reportedReviews.map { it.entityId }.distinct()
        val foundReviews = reviewRepository.findAllById(reportedReviewIds)
        return foundReviews.map { ReviewDto.from(it) }
    }
}