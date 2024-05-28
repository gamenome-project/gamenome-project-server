package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

import org.springframework.context.annotation.Description

data class ReportCommentRequestDto(
    val description: String,
    val entityType: String = "Comment",
)
