package sparta.nbcamp.gamenomeprojectserver.domain.comment.model.v1

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.SQLRestriction
import org.hibernate.annotations.UpdateTimestamp
import jakarta.persistence.Table
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1.CreateCommentRequestDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review
import sparta.nbcamp.gamenomeprojectserver.domain.starScore.model.v1.StarScore
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User
import java.time.LocalDateTime

@SQLDelete(sql = "UPDATE comment c SET c.deleted_at = current_timestamp AND c.is_deleted = true WHERE c.id = ?")
@SQLRestriction("is_deleted = false")
@Entity
@Table(name = "comment")
class Comment private constructor(
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

    fun updateContent(content: String) {
        this.content = content

        this.validate()
    }

    private fun validate() {
        require(this.content.isNotBlank()) { "Content must not be blank" }
        require(this.content.length <= 100) { "Content must be 100 characters or less" }
    }

    companion object{
        fun fromDto(createCommentRequestDto: CreateCommentRequestDto, review : Review, user: User): Comment {
            return Comment(
                user = user,
                review = review,
                content = createCommentRequestDto.content
            ).apply { this.validate() }
        }
    }
}