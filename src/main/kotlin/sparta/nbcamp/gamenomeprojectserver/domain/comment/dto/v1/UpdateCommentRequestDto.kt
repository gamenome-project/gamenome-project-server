package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

data class UpdateCommentRequestDto(
    override val content: String,
) : CommentContentValidatableDto(content)