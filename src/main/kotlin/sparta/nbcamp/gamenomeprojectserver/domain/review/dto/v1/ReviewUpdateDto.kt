package sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1

data class ReviewUpdateDto(
    override val gameName: String,
    override val title: String,
    override val description: String,
    ) : ReviewValidatableDto(gameName, title, description)
