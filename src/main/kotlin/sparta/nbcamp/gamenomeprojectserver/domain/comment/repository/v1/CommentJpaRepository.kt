package sparta.nbcamp.gamenomeprojectserver.domain.comment.repository.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.v1.Comment

interface CommentJpaRepository: JpaRepository<Comment, Long> {

    fun findAllByReviewId(reviewId: Long, pageable: Pageable = Pageable.unpaged()): Page<Comment>

    fun findByIdAndReviewId(reviewId: Long, commentId: Long): Comment

}