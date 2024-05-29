package sparta.nbcamp.gamenomeprojectserver.domain.reaction.service.v1

import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.v1.Comment
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.entity.v1.Reaction
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.entity.v1.ReactionType
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.repository.v1.ReactionRepository

@Service
class ReactionService(
    private val reactionRepository: ReactionRepository
) {


    fun update(comment: Comment, reactionType: ReactionType){
        val result = reactionRepository.findByCommentIdAndReaction(comment.id!!, reactionType)
        if (result == null) {
            reactionRepository.save(
                Reaction(
                    user = TODO(),
                    comment = comment,
                    reaction = reactionType
                )
            )
        }else{
            if(result.reaction == ReactionType.Like) result.reaction = ReactionType.DisLike
            else result.reaction = ReactionType.Like
        }

    }
}