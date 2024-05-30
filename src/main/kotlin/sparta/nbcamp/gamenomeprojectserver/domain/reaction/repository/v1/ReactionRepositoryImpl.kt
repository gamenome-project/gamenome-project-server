package sparta.nbcamp.gamenomeprojectserver.domain.reaction.repository.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.entity.v1.Reaction
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.entity.v1.ReactionType

@Repository
class ReactionRepositoryImpl(
    val reactionJpaRepository: ReactionJpaRepository
) : ReactionRepository {
    override fun findAll(pageable: Pageable): Page<Reaction> {
        return reactionJpaRepository.findAll(pageable)
    }

    override fun findByCommentIdAndReaction(commentId: Long, reaction: ReactionType): Reaction? {
        return reactionJpaRepository.findByCommentIdAndReaction(commentId, reaction)
    }

    override fun save(reaction: Reaction): Reaction {
        return reactionJpaRepository.save(reaction)
    }

    override fun delete(reaction: Reaction) {
        reactionJpaRepository.delete(reaction)
    }

    override fun deleteById(reactionId: Long) {
        reactionJpaRepository.deleteById(reactionId)
    }

    override fun deleteAllByCommentId(commentId: Long) {
        reactionJpaRepository.deleteAllByCommentId(commentId)
    }
}