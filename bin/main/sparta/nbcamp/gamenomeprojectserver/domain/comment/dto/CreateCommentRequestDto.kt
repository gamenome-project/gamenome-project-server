package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto

data class CreateCommentRequestDto(
    val content: String,
    val stars: Double,
)
