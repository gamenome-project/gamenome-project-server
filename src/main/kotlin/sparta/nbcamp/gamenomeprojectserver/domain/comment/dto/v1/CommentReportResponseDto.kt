package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

data class CommentReportResponseDto (
    val userId: Long,
    val description: String,
    val entityType: String,
)
