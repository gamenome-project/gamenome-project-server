package sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import sparta.nbcamp.gamenomeprojectserver.common.setStarScore
import sparta.nbcamp.gamenomeprojectserver.domain.comment.service.v1.CommentService
import sparta.nbcamp.gamenomeprojectserver.domain.follow.service.v1.FollowService
import sparta.nbcamp.gamenomeprojectserver.domain.report.model.v1.EntityType
import sparta.nbcamp.gamenomeprojectserver.domain.report.service.ReportService
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.*
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review
import sparta.nbcamp.gamenomeprojectserver.domain.review.repository.v1.ReviewRepository
import sparta.nbcamp.gamenomeprojectserver.domain.security.service.AuthService
import sparta.nbcamp.gamenomeprojectserver.domain.starScore.service.v1.StarScoreService
import sparta.nbcamp.gamenomeprojectserver.domain.user.repository.UserRepository
import sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1.UserService
import sparta.nbcamp.gamenomeprojectserver.exception.ModelNotFoundException

@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository,
    private val reportService: ReportService,
    private val userRepository: UserRepository,
    private val commentService: CommentService,
    private val starScoreService: StarScoreService,
    private val followService: FollowService,
    private val authService: AuthService,
) : ReviewService {
    @Transactional
    override fun createReview(reviewCreateDTO: ReviewCreateDto, token: String): ReviewDto {

        val userId = authService.getUserIdFromToken(token)
        val user = userRepository.find(userId)?: throw ModelNotFoundException("User", userId)

        val review = Review.fromDto(reviewCreateDTO, user)
        return ReviewDto.from(reviewRepository.save(review))
    }

    override fun getReviewPage(pageable: Pageable): Page<ReviewDto> {

        val reportReview = reportService.getReportsByEntityType(EntityType.Review).distinct()

        val foundAllReview = reviewRepository.findAll(pageable)

        foundAllReview.removeAll { review ->
            reportReview.any { report ->
                review.id == report.entityId && reportService.getCountByEntityIdAndEntityType(report.entityId, EntityType.Review) > 5
            }
        }

        return foundAllReview.map { ReviewDto.from(it) }
    }

    @Transactional
    override fun updateReview(reviewId: Long, reviewUpdateDTO: ReviewUpdateDto, token: String): ReviewDto {

        val foundReview = reviewRepository.find(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        foundReview.updateReviewField(reviewUpdateDTO)

        return ReviewDto.from(foundReview)
    }

    @Transactional
    override fun deleteReview(reviewId: Long) {
        reviewRepository.deleteById(reviewId)
        commentService.deleteCommentsByReviewId(reviewId)
    }

    override fun getReview(reviewId: Long): GetReviewDto {
        val foundReview = reviewRepository.find(reviewId) ?: throw ModelNotFoundException("Review", reviewId)
        val result = starScoreService.getAverageScore(reviewId)

        if(reportService.getCountByEntityIdAndEntityType(reviewId, EntityType.Review) > 5) throw ModelNotFoundException("Report review", reviewId)

        return GetReviewDto.from(foundReview, setStarScore(result))
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

    override fun getFollowingReviewPage(token: String, pageable: Pageable): Page<ReviewDto> {
        val userId = authService.getUserIdFromToken(token)
        val followingResult = followService.followRepository.findAllByUserId(userId)

       val result = reviewRepository.findAllByManyUserId(followingResult.map { it.followingUserId }, pageable)



        return result.map { ReviewDto.from(it) }
    }
}