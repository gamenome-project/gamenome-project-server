package sparta.nbcamp.gamenomeprojectserver.domain.reaction.model.v1

import jakarta.persistence.*
import sparta.nbcamp.gamenomeprojectserver.domain.comment.model.v1.Comment
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User

@IdClass(ReactionId::class)
@Entity
@Table(name = "reaction")
class Reaction(

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    val comment: Comment,

    @Enumerated(EnumType.STRING)
    @Column(name = "reaction", nullable = false)
    var reaction: ReactionType,

    )