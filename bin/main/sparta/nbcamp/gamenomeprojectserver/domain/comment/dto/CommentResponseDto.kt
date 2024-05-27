package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto

import java.time.LocalDateTime

data class CommentResponseDto(

    val id: Long,

    val userId: Long,

    val reviewId: Long,

    val content: String,

    val createdAt: LocalDateTime,

    val updatedAt: LocalDateTime,
)
