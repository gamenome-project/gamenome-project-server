package sparta.nbcamp.gamenomeprojectserver.domain.report.dto.v1

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ReportReviewDto(
    @field:NotBlank
    @field:Size(max = 200, message = "Content must be 200 characters or less")
    val description: String
)