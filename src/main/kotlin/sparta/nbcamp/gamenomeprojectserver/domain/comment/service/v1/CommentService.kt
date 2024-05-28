package sparta.nbcamp.gamenomeprojectserver.domain.comment.service.v1

import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1.*
import sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.v1.Comment
import sparta.nbcamp.gamenomeprojectserver.domain.comment.repository.v1.CommentRepository
import sparta.nbcamp.gamenomeprojectserver.domain.report.dto.v1.ReportReviewDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.repository.v1.ReviewRepository
import sparta.nbcamp.gamenomeprojectserver.domain.user.service.v1.UserService
import sparta.nbcamp.gamenomeprojectserver.exception.ModelNotFoundException

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val reviewRepository: ReviewRepository,
    private val userService: UserService
) {

    @Transactional
    fun createComment(reviewId: Long, createCommentRequestDto: CreateCommentRequestDto): CommentResponseDto {
        //TODO("유저 로그인 검증 및 블랙 리스트 검증")
        val reviewResult = reviewRepository.findByIdOrNull(reviewId)?: throw ModelNotFoundException("review", reviewId )

        val result = CreateCommentRequestDto.create(createCommentRequestDto, reviewResult, userService.TODO())

        return CommentResponseDto.from(result)

    }

    fun getCommentList(reviewId: Long,): List<CommentResponseDto>{
        //TODO("리뷰 아이디에 대한 코맨트 조회 없으면 throw ModelNotFoundException")
        commentRepository.findByReviewId(reviewId)?: throw ModelNotFoundException("comment", reviewId )
        val result = commentRepository.findAll()
        return result.map{ CommentResponseDto.from(it) }
        //TODO("조회 시에 신고 된 데이터는 조회 하지 않음")
    }

    @Transactional
    fun updateComment(reviewId: Long, commentId: Long, updateCommentRequestDto: UpdateCommentRequestDto
    ): CommentResponseDto {
        //TODO("유저 로그인 검증 및 블랙 리스트 검증")
        commentRepository.findByIdOrNull(commentId)?: throw ModelNotFoundException("comment", reviewId )

        val result = commentRepository.findByIdAndReviewId(reviewId, commentId)

        if(result.isDeleted) throw ModelNotFoundException("comment", reviewId )

        result.update(updateCommentRequestDto.content)

        return CommentResponseDto.from(result)
    }

    @Transactional
    fun deleteComment(reviewId: Long, commentId: Long,){
        //TODO("유저 로그인 검증 및 블랙 리스트 검증")
        reviewRepository.findByIdOrNull(reviewId)?: throw ModelNotFoundException("review", reviewId )
        val result = commentRepository.findByIdOrNull(commentId)?: throw ModelNotFoundException("comment", reviewId )

        result.isDeleted = true

        commentRepository.save(result)

        //TODO("deleteComment 이벤트 발생 시에 deletedAt이 업데이트 된 데이터가 특정 개수를 넘어가게 되면 delete 이벤트 발생")

        TODO()
    }

    fun createReportComment(reviewId: Long, commentId: Long, reportReviewDto: ReportReviewDto): CommentReportResponseDto {
        //TODO("유저 로그인 검증")
        //TODO("신고 내용 작성 후에 추가")
        //TODO("신고 시에 put 이벤트 발생 으로 deletedAt 상태로 업데이트")
        //TODO("CommentReportResponseDto 로 신고 정보 리턴")

        TODO()
    }

}