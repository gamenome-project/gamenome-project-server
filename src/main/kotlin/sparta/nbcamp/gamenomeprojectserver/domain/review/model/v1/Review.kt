package sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewUpdateDto
import java.time.LocalDateTime

@Entity
@Table(name = "review")
class Review(
    @Column(name = "game_name", nullable = false)
    var gameName: String,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "description", nullable = false)
    var description: String,

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    val updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at", nullable = true)
    var deletedAt: LocalDateTime? = null
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun updateReviewField(reviewUpdateDto: ReviewUpdateDto) {
        gameName = reviewUpdateDto.gameName
        title = reviewUpdateDto.title
        description = reviewUpdateDto.description
    }
}
