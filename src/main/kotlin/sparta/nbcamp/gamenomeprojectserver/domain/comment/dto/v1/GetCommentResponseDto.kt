package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.comment.model.v1.Comment
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.dto.v1.ReactionResponseDto
import java.time.LocalDateTime

data class GetCommentResponseDto(

    val id: Long,

    val userId: Long,

    val reviewId: Long,

    val content: String,

    val createdAt: LocalDateTime,

    val updatedAt: LocalDateTime,

    val like: Int,

    val dislike: Int,

    ){
    companion object {
        fun from(comment: Comment, reactionResponseDto: ReactionResponseDto): GetCommentResponseDto {
            return GetCommentResponseDto(
                id = comment.id!!,
                userId = comment.user.id!!,
                reviewId = comment.review.id!!,
                content = comment.content,
                createdAt = comment.createdAt,
                updatedAt = comment.updatedAt?:comment.createdAt,
                like = reactionResponseDto.like,
                dislike = reactionResponseDto.dislike,
            )
        }
    }
}
