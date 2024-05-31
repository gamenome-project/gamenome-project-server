package sparta.nbcamp.gamenomeprojectserver.domain.starScore.service.v1

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.comment.model.v1.Comment
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review
import sparta.nbcamp.gamenomeprojectserver.domain.starScore.model.v1.StarScore
import sparta.nbcamp.gamenomeprojectserver.domain.starScore.repository.v1.StarScoreRepository
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

@Service
class StarScoreService(
    private val starScoreRepository: StarScoreRepository,
    @PersistenceContext private val em: EntityManager
) {

    @Transactional
    fun giveCommentScore(review: Review, user: User, comment: Comment, starScore:Float){

            val result = StarScore.create(user, review, comment, starScore)
            starScoreRepository.save(result)
    }

    fun delete(comment: Comment){

        starScoreRepository.deleteByCommentId(comment.id!!)
    }

//    fun getAverageScore(review: Review):Float{
//
//        val peopleCount = starScoreRepository.countByReviewId(review.id)
//
//    }
}