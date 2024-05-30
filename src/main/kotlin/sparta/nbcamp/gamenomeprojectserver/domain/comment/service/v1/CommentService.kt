package sparta.nbcamp.gamenomeprojectserver.domain.comment.service.v1

import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1.*
import sparta.nbcamp.gamenomeprojectserver.domain.comment.model.v1.Comment
import sparta.nbcamp.gamenomeprojectserver.domain.comment.repository.v1.CommentRepository
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.model.v1.ReactionType
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.service.v1.ReactionService
import sparta.nbcamp.gamenomeprojectserver.domain.report.model.v1.EntityType
import sparta.nbcamp.gamenomeprojectserver.domain.report.service.ReportService
import sparta.nbcamp.gamenomeprojectserver.domain.review.repository.v1.ReviewJpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.security.service.AuthService
import sparta.nbcamp.gamenomeprojectserver.domain.user.repository.UserRepository
import sparta.nbcamp.gamenomeprojectserver.exception.ModelNotFoundException

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val reviewRepository: ReviewJpaRepository,
    private val userRepository: UserRepository,

    private val authService: AuthService,
    private val reactionService: ReactionService,
    private val reportService: ReportService,
) {

    @Transactional
    fun createComment(reviewId: Long, createCommentRequestDto: CreateCommentRequestDto, token:String): CommentResponseDto {

        val user = authService.getUserIdFromToken(token)

        val userResult = userRepository.find(user)?: throw ModelNotFoundException("User", user)

        val reviewResult = reviewRepository.findByIdOrNull(reviewId)?: throw ModelNotFoundException("review", reviewId )

        val result = Comment.fromDto(createCommentRequestDto, reviewResult, userResult)

        commentRepository.save(result)

        return CommentResponseDto.from(result)

    }

    fun getCommentPage(reviewId: Long, pageable: Pageable): Page<GetCommentResponseDto>{
        //TODO("리뷰 아이디에 대한 코맨트 조회 없으면 throw ModelNotFoundException")
        if(!reviewRepository.existsById(reviewId)) throw ModelNotFoundException("review", reviewId )
        val result = commentRepository.findAllByReviewId(reviewId, pageable)
        return result.map{ GetCommentResponseDto.from(it) }
        //TODO("조회 시에 신고 된 데이터는 조회 하지 않음")
    }

    @Transactional
    fun updateComment(reviewId: Long, commentId: Long, updateCommentRequestDto: UpdateCommentRequestDto
    ): CommentResponseDto {
        //TODO("유저 로그인 검증 및 블랙 리스트 검증")
        val result = commentRepository.findByIdAndReviewId(reviewId, commentId) ?: throw ModelNotFoundException("comment", commentId)

        if(result.isDeleted) throw RuntimeException("이미 삭제된 댓글입니다.")

        result.updateContent(updateCommentRequestDto.content)

        return CommentResponseDto.from(result)
    }

    @Transactional
    fun deleteComment(reviewId: Long, commentId: Long) {
        //TODO("유저 로그인 검증 및 블랙 리스트 검증")
        val result = commentRepository.findByIdAndReviewId(reviewId, commentId) ?: throw ModelNotFoundException("comment", commentId)

        commentRepository.delete(result)
        reactionService.delete(result)
    }

    fun createReportComment(
        reviewId: Long,
        commentId: Long,
        reportCommentRequestDto: ReportCommentRequestDto
    ): CommentReportResponseDto {
        val user = userRepository.find(reportCommentRequestDto.userId) ?: throw ModelNotFoundException(
            "User",
            reportCommentRequestDto.userId
        )
        val report = reportService.createCommentReport(user, commentId, EntityType.Comment, reportCommentRequestDto)

        return CommentReportResponseDto.from(report)
    }

    fun commentLikeReaction(reviewId: Long, commentId: Long, token: String) {
        if(!reviewRepository.existsById(reviewId)) throw ModelNotFoundException("review", reviewId )

        val commentResult = commentRepository.find(commentId)?: throw ModelNotFoundException("comment", commentId )
        val userId = authService.getUserIdFromToken(token)

        reactionService.update(commentResult, ReactionType.Like, userId)
    }

    fun commentDisLikeReaction(reviewId: Long, commentId: Long, token: String) {
        if(!reviewRepository.existsById(reviewId)) throw ModelNotFoundException("review", reviewId )

        val commentResult = commentRepository.find(commentId)?: throw ModelNotFoundException("comment", commentId )
        val userId = authService.getUserIdFromToken(token)

        reactionService.update(commentResult, ReactionType.DisLike, userId)
    }
}