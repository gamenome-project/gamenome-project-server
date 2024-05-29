package sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class ReviewReportDto(
    val userId: Long,
    @field:NotBlank
    @field:Size(max = 200, message = "Description must be 200 characters or less")
    val description: String
)
