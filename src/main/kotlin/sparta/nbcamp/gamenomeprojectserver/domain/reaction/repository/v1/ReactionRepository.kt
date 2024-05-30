package sparta.nbcamp.gamenomeprojectserver.domain.reaction.repository.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.model.v1.Reaction
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.model.v1.ReactionType

interface ReactionRepository {
    fun findAll(pageable: Pageable = Pageable.unpaged()): Page<Reaction>
    fun findByCommentIdAndReaction(commentId: Long, reaction: ReactionType): Reaction?
    fun save(reaction: Reaction): Reaction
    fun delete(reaction: Reaction)
    fun deleteById(reactionId: Long)
    fun deleteAllByCommentId(commentId: Long)
}