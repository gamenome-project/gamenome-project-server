package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

data class ReportCommentRequestDto(
    val userId: Long,
    val description: String,
    val entityType: String = "Comment",
)
