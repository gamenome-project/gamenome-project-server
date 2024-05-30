package sparta.nbcamp.gamenomeprojectserver.domain.comment.repository.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.comment.model.v1.Comment


interface CommentRepository: JpaRepository<Comment, Long> {

//    @Query("SELECT c FROM Comment c WHERE c.isDeleted == false ORDER BY c.createdAt ASC")
//    fun findAllByReviewIdNotDeletedAt(reviewId: Long): List<Comment>

    fun findAllByReviewId(reviewId: Long, pageable: Pageable = Pageable.unpaged()): Page<Comment>


    fun findByIdAndReviewId(reviewId: Long, commentId: Long): Comment

}