package sparta.nbcamp.gamenomeprojectserver.domain.comment.repository.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.v1.Comment


interface CommentRepository: JpaRepository<Comment, Long> {

//    @Query("SELECT c FROM Comment c WHERE c.isDeleted == false ORDER BY c.createdAt ASC")
//    fun findAllByReviewIdNotDeletedAt(reviewId: Long): List<Comment>

    fun findAllByReviewId(reviewId: Long, pageable: Pageable): Page<Comment>


    fun findByIdAndReviewId(reviewId: Long, commentId: Long): Comment

    fun findByReviewId(reviewId: Long,): Comment?
}