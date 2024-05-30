package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.comment.model.v1.Comment
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

data class CreateCommentRequestDto(
    override val content: String,

    val stars: Double,
): CommentContentValidatableDto(content)

