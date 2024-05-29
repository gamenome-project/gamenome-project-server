package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.v1.Comment
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User
import java.time.LocalDateTime

data class CreateCommentRequestDto(
    override val content: String,

    val stars: Double,
): CommentContentValidatableDto(content)
