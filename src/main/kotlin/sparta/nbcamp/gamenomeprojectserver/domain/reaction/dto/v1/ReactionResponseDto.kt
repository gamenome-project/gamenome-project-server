package sparta.nbcamp.gamenomeprojectserver.domain.reaction.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.reaction.model.v1.Reaction

data class ReactionResponseDto(
    val commentId: Long,
    val like: Int,
    val dislike: Int,
)