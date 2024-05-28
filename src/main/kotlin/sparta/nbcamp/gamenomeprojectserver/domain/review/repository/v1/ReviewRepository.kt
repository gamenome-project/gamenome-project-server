package sparta.nbcamp.gamenomeprojectserver.domain.review.repository.v1

import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review

interface ReviewRepository : JpaRepository<Review, Long> {
}