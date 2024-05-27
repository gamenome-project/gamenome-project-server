package sparta.nbcamp.gamenomeprojectserver.domain.comment.service.v1

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1.CommentResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1.CreateCommentRequestDto
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1.UpdateCommentRequestDto

@Service
class CommentService {

    @Transactional
    fun createComment(reviewId: Long, createCommentRequestDto: CreateCommentRequestDto): CommentResponseDto {
        TODO()
    }

    fun getCommentList(reviewId: Long,): List<CommentResponseDto>{
        TODO()
    }

    @Transactional
    fun updateComment(reviewId: Long, commentId: Long, updateCommentRequestDto: UpdateCommentRequestDto
    ): CommentResponseDto {
        TODO()
    }

    @Transactional
    fun deleteComment(reviewId: Long, commentId: Long,){
        TODO()
    }

}