package sparta.nbcamp.gamenomeprojectserver.domain.comment.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.CommentResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.CreateCommentRequestDto
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.UpdateCommentRequestDto

@Service
class CommentService {

    @Transactional
    fun createComment(reviewId: Long, createCommentRequestDto: CreateCommentRequestDto): CommentResponseDto{
        TODO()
    }

    fun getCommentList(reviewId: Long,): List<CommentResponseDto>{
        TODO()
    }

    @Transactional
    fun updateComment(reviewId: Long, commentId: Long, updateCommentRequestDto: UpdateCommentRequestDto
    ):CommentResponseDto{
        TODO()
    }

    @Transactional
    fun deleteComment(reviewId: Long, commentId: Long,){
        TODO()
    }

}