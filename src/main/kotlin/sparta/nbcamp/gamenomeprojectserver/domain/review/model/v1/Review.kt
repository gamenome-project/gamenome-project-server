package sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import org.hibernate.annotations.UpdateTimestamp
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewCreateDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewUpdateDto
import java.time.LocalDateTime

@Entity
@Table(name = "review")
@SQLDelete(sql = "UPDATE review SET is_deleted = true, deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@SQLRestriction("is_deleted = false")
class Review private constructor(
    @Column(name = "game_name", columnDefinition = "text", nullable = false)
    var gameName: String,

    @Column(name = "title", columnDefinition = "text", nullable = false)
    var title: String,

    @Column(name = "description", columnDefinition = "text", nullable = false)
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

        this.validate()
    }

    private fun validate() {
        require(gameName.isNotBlank()) { "Game name cannot be blank" }
        require(gameName.length <= 100) { "Game name must be 100 characters or less" }

        require(title.isNotBlank()) { "Title cannot be blank" }
        require(title.length <= 200) { "Title must be 200 characters or less" }

        require(description.isNotBlank()) { "Description must not be blank" }
        require(description.length <= 2000) { "Description must be 2000 characters or less" }
    }

    companion object {
        fun fromDto(reviewCreateDto: ReviewCreateDto): Review {
            return Review(
                gameName = reviewCreateDto.gameName,
                title = reviewCreateDto.title,
                description = reviewCreateDto.description,
            ).apply { this.validate() }
        }
    }
}
