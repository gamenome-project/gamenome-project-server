package sparta.nbcamp.gamenomeprojectserver.domain.review.repository.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import sparta.nbcamp.gamenomeprojectserver.domain.review.model.v1.Review

interface ReviewJpaRepository : JpaRepository<Review, Long>{

    @Query("SELECT r FROM Review r where r.user.id in (:userIds)")
    fun findAllByManyUserId(@Param("userIds")userId: List<Long>, pageable: Pageable): Page<Review>
}