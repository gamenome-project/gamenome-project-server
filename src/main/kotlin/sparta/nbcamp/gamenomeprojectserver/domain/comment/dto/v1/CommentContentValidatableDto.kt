package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

abstract class CommentContentValidatableDto(
    @field:NotBlank
    @field:Size(max = 200, message = "Content must be 200 characters or less")
    open val content: String
)