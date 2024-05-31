package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

data class UpdateCommentRequestDto(
    override val content: String,
    override val score: Float,
) : CommentContentValidatableDto(content)