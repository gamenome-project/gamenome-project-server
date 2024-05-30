package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.comment.model.v1.Comment
import java.time.LocalDateTime

data class GetCommentResponseDto(

    val id: Long,

    val userId: Long,

    val reviewId: Long,

    val content: String,

    val createdAt: LocalDateTime,

    val updatedAt: LocalDateTime,

    val likes: Int,

    val disLikes: Int,
){
    companion object {
        fun from(comment: Comment, likes: Int, disLikes: Int): GetCommentResponseDto {
            return GetCommentResponseDto(
                id = comment.id!!,
                userId = comment.user.id!!,
                reviewId = comment.review.id!!,
                content = comment.content,
                createdAt = comment.createdAt,
                updatedAt = comment.updatedAt?:comment.createdAt,
                likes = likes,
                disLikes = disLikes,
            )

        }
    }
}
