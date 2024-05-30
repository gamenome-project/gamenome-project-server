package sparta.nbcamp.gamenomeprojectserver.domain.reaction.repository.v1

import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.entity.v1.Reaction
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.entity.v1.ReactionType

interface ReactionJpaRepository: JpaRepository<Reaction, Long> {

    fun findByCommentIdAndReaction(commentId: Long, reaction: ReactionType):Reaction?

    fun deleteAllByCommentId(commentId: Long)
}