package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import sparta.nbcamp.gamenomeprojectserver.common.annotation.FloatRange

abstract class CommentContentValidatableDto(
    @field:NotBlank
    @field:Size(max = 200, message = "Content must be 200 characters or less")
    open val content: String,

    @field:NotBlank
    @field:FloatRange(min = 0f, max = 5f)
    open val score: Float = 0.0f,
)