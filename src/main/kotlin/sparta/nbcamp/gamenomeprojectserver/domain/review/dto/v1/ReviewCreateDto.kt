package sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1

data class ReviewCreateDto(
    val gameName: String,
    val title: String,
    val description: String,
)
