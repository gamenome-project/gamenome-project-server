package sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.v1

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User
import java.time.LocalDateTime

@Entity
@Table(name = "comment")
class Comment(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    val review: Review,

    @Column(name="content", columnDefinition = "text", nullable = false)
    var content: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    val updatedAt: LocalDateTime? = null

    @Column(name = "deleted_at", nullable = true)
    val deletedAt: LocalDateTime? = null

    fun update(comment: String) {
        this.content = comment
    }
}