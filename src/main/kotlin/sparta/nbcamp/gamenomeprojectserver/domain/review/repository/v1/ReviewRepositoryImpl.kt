package sparta.nbcamp.gamenomeprojectserver.domain.review.repository.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review

@Repository
class ReviewRepositoryImpl(
    val reviewJpaRepository: ReviewJpaRepository
) : ReviewRepository {
    override fun findAll(pageable: Pageable): Page<Review> {
        return reviewJpaRepository.findAll(pageable)
    }

    override fun findAllById(reviewIds: List<Long>): List<Review> {
        return reviewJpaRepository.findAllById(reviewIds)
    }

    override fun find(reviewId: Long): Review? {
        return reviewJpaRepository.findByIdOrNull(reviewId)
    }

    override fun exists(reviewId: Long): Boolean {
        return reviewJpaRepository.existsById(reviewId)
    }

    override fun findAllByManyUserId(userId: List<Long>, pageable: Pageable): Page<Review> {
        return reviewJpaRepository.findAllByManyUserId(userId, pageable)
    }

    override fun save(review: Review): Review {
        return reviewJpaRepository.save(review)
    }

    override fun delete(review: Review) {
        reviewJpaRepository.delete(review)
    }

    override fun deleteById(reviewId: Long) {
        reviewJpaRepository.deleteById(reviewId)
    }
}