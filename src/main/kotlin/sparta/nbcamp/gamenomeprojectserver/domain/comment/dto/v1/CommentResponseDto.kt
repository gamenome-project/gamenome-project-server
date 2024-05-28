package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.v1.Comment
import java.time.LocalDateTime

data class CommentResponseDto(

    val id: Long,

    val userId: Long,

    val reviewId: Long,

    val content: String,

    val createdAt: LocalDateTime,

    val updatedAt: LocalDateTime,
){
    companion object {
        fun from(comment: Comment): CommentResponseDto {
            return CommentResponseDto(
                id = comment.id!!,
                userId = comment.user.id!!,
                reviewId = comment.review.id!!,
                content = comment.content,
                createdAt = comment.createdAt,
                updatedAt = comment.updatedAt?:comment.createdAt,
            )

        }
    }
}
