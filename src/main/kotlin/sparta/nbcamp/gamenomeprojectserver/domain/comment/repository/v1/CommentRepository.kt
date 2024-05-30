package sparta.nbcamp.gamenomeprojectserver.domain.comment.repository.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.v1.Comment

interface CommentRepository {
    fun findAllByReviewId(reviewId: Long, pageable: Pageable = Pageable.unpaged()): Page<Comment>
    fun findByIdAndReviewId(commentId: Long, reviewId: Long): Comment?
    fun find(commentId: Long): Comment?
    fun exists(commentId: Long): Boolean
    fun save(comment: Comment): Comment
    fun delete(comment: Comment)
    fun deleteByIdAndReviewId(commentId: Long, reviewId: Long)
}