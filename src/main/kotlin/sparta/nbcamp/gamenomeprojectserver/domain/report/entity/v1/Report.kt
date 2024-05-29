package sparta.nbcamp.gamenomeprojectserver.domain.report.entity.v1

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewReportDto
import sparta.nbcamp.gamenomeprojectserver.domain.user.model.User
import java.time.LocalDateTime


@Entity
@Table(name = "report")
class Report(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "entity_id", nullable = false)
    val entityId: Long,

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type", nullable = false)
    val entityType: EntityType,

    @Column(name = "description", nullable = false)
    val description: String,


    ) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    companion object {
        fun fromDto(user: User, reviewId: Long, entityType: EntityType, reviewReportDto: ReviewReportDto): Report {
            return Report(
                user = user,
                entityId = reviewId,
                entityType = entityType,
                description = reviewReportDto.description
            )
        }
    }
}