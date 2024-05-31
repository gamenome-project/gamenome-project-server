package sparta.nbcamp.gamenomeprojectserver.domain.review.repository.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review

interface ReviewRepository {
    fun findAll(pageable: Pageable = Pageable.unpaged()): Page<Review>
    fun findAllById(reviewIds: List<Long>): List<Review>
    fun findAllByManyUserId(userId: List<Long>, pageable: Pageable = Pageable.unpaged()): Page<Review>
    fun find(reviewId: Long): Review?
    fun exists(reviewId: Long): Boolean
    fun save(review: Review): Review
    fun delete(review: Review)
    fun deleteById(reviewId: Long)
}