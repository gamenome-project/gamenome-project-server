package sparta.nbcamp.gamenomeprojectserver.domain.comment.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
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

    @Column(name="content", nullable = false)
    val content: String,

    @Column(name = "is_deleted", nullable = false)
    val isDeleted: Boolean = false,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    val updatedAt: LocalDateTime?,

    @Column(name = "deleted_at", nullable = true)
    val deletedAt: LocalDateTime? = null

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}