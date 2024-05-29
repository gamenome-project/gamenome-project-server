package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.context.annotation.Description

data class ReportCommentRequestDto(
    @field:NotBlank
    @field:Size(max = 200, message = "Description must be 200 characters or less")
    val description: String,
    val entityType: String = "Comment",
)
