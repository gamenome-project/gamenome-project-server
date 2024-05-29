package sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

abstract class ReviewValidatableDto(
    @field:NotBlank
    @field:Size(max = 100, message = "Game name must be 100 characters or less")
    open val gameName: String,

    @field:NotBlank
    @field:Size(max = 200, message = "Title must be 200 characters or less")
    open val title: String,

    @field:NotBlank
    @field:Size(max = 2000, message = "Description must be 2000 characters or less")
    open val description: String,
)