package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

data class CreateCommentRequestDto(
    val content: String,
    val stars: Double,
)
