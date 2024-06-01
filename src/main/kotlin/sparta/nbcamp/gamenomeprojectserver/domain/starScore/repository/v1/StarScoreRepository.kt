package sparta.nbcamp.gamenomeprojectserver.domain.starScore.repository.v1

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import sparta.nbcamp.gamenomeprojectserver.domain.starScore.model.v1.StarScore


@EnableJpaRepositories
interface StarScoreRepository: JpaRepository<StarScore, Long> {

    fun existsByCommentId(commentId: Long):Boolean

    fun findByCommentId(commentId: Long): StarScore

    fun deleteByCommentId(commentId: Long)

    fun countByReviewId(reviewId: Long): Int

    @Query("SELECT avg(s.score) FROM StarScore s WHERE s.review.id = :reviewId")
    fun averageByReviewId(reviewId: Long):Float?

}