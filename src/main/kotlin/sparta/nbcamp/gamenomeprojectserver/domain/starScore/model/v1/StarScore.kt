package sparta.nbcamp.gamenomeprojectserver.domain.starScore.model.v1

import jakarta.persistence.*
import org.hibernate.annotations.NotFound
import org.hibernate.annotations.NotFoundAction
import sparta.nbcamp.gamenomeprojectserver.domain.comment.model.v1.Comment
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

@IdClass(StarScoreId::class)
@Entity
@Table(name = "star_score")
class StarScore(

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    val user: User,

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="review_id", nullable = false)
    val review: Review,

    @Id
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.MERGE])
    @JoinColumn(name="comment_id", nullable = false)
    val comment: Comment,

    @Column(name = "score", nullable = false)
    var score: Float,

    ){


    companion object {
        fun create(user: User, review: Review, comment: Comment, score: Float): StarScore {
            return StarScore(
                    review = review,
                    user = user,
                    comment = comment,
                    score = score
                    )
        }
    }

}