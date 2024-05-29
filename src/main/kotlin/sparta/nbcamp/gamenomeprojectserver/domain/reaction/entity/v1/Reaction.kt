package sparta.nbcamp.gamenomeprojectserver.domain.reaction.entity.v1

import jakarta.persistence.*
import sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.v1.Comment
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

@Entity
@Table(name = "reaction")
class Reaction(

    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @OneToOne
    @JoinColumn(name = "comment_id", nullable = false)
    val comment: Comment,

    @Enumerated(EnumType.STRING)
    @Column(name = "reaction", nullable = false)
    var reaction: ReactionType,

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}