package sparta.nbcamp.gamenomeprojectserver.domain.comment.service.v1

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1.*

@Service
class CommentService {

    @Transactional
    fun createComment(reviewId: Long, createCommentRequestDto: CreateCommentRequestDto): CommentResponseDto {
        //TODO("유저 로그인 검증 및 블랙 리스트 검증")
        //TODO("리뷰 아이디에 대한 댓글 작성")

        TODO()
    }

    fun getCommentList(reviewId: Long,): List<CommentResponseDto>{
        //TODO("리뷰 아이디에 대한 코맨트 조회 없으면 throw ModelNotFoundException")
        //TODO("조회 시에 Delete 된 객체는 조회 하지 않음 deletedAt != null 일 경우 조회 X")
        //TODO("조회 시에 신고 된 데이터는 조회 하지 않음")
        //TODO("조회 시에 createdAt 기준 오름차순 으로 조회")
        TODO()
    }

    @Transactional
    fun updateComment(reviewId: Long, commentId: Long, updateCommentRequestDto: UpdateCommentRequestDto
    ): CommentResponseDto {
        //TODO("유저 로그인 검증 및 블랙 리스트 검증")
        //TODO("리뷰 아이디에 대한 코맨트 조회 없으면 throw ModelNotFoundException")
        //TODO("조회 시에 Delete 된 객체는 업데이트 하지 않음 deletedAt != null 일 경우 업데이트 X")


        TODO()
    }

    @Transactional
    fun deleteComment(reviewId: Long, commentId: Long,){
        //TODO("유저 로그인 검증 및 블랙 리스트 검증")
        //TODO("리뷰 아이디에 대한 코맨트 조회 없으면 throw ModelNotFoundException")
        //TODO("아이디 삭제 시에 put 이벤트 발생 으로 deletedAt 상태를 업데이트")
        //TODO("deleteComment 이벤트 발생 시에 deletedAt이 업데이트 된 데이터가 특정 개수를 넘어가게 되면 delete 이벤트 발생")

        TODO()
    }

    fun createReportComment(reviewId: Long, commentId: Long, reportCommentRequestDto: ReportCommentRequestDto): CommentReportResponseDto {
        //TODO("유저 로그인 검증")
        //TODO("신고 내용 작성 후에 추가")
        //TODO("신고 시에 put 이벤트 발생 으로 deletedAt 상태로 업데이트")
        //TODO("CommentReportResponseDto 로 신고 정보 리턴")

        TODO()
    }

}