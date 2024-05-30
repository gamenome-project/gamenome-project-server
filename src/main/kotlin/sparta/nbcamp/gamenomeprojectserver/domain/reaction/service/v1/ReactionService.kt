package sparta.nbcamp.gamenomeprojectserver.domain.reaction.service.v1

import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.v1.Comment
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.entity.v1.Reaction
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.entity.v1.ReactionType
import sparta.nbcamp.gamenomeprojectserver.domain.reaction.repository.v1.ReactionRepository
import sparta.nbcamp.gamenomeprojectserver.domain.user.repository.UserRepository
import sparta.nbcamp.gamenomeprojectserver.exception.ModelNotFoundException

@Service
class ReactionService(
    private val reactionRepository: ReactionRepository,
    private val userRepository: UserRepository,
) {

    fun update(comment: Comment, reactionType: ReactionType, userId: Long){
        val result = reactionRepository.findByCommentIdAndReaction(comment.id!!, reactionType)
        val user = userRepository.find(userId)?: throw ModelNotFoundException("User", userId)
        if (result == null) {
            reactionRepository.save(
                Reaction(
                    user = user,
                    comment = comment,
                    reaction = reactionType
                )
            )
        }else if(reactionType == result.reaction) reactionRepository.delete(result)
        else result.reaction = reactionType
    }

    fun delete(comment: Comment,){
        reactionRepository.deleteAllById(mutableListOf(comment.id))
    }

}