package sparta.nbcamp.gamenomeprojectserver.domain.comment.repository.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import sparta.nbcamp.gamenomeprojectserver.domain.comment.model.v1.Comment

@Repository
class CommentRepositoryImpl(
    val commentJpaRepository: CommentJpaRepository
) : CommentRepository {
    override fun findAllByReviewId(reviewId: Long, pageable: Pageable): Page<Comment> {
        return commentJpaRepository.findAllByReviewId(reviewId, pageable)
    }

    override fun findByIdAndReviewId(commentId: Long, reviewId: Long): Comment? {
        return commentJpaRepository.findByIdAndReviewId(reviewId, commentId)
    }

    override fun find(commentId: Long): Comment? {
        return commentJpaRepository.findByIdOrNull(commentId)
    }

    override fun exists(commentId: Long): Boolean {
        return commentJpaRepository.existsById(commentId)
    }

    override fun save(comment: Comment): Comment {
        return commentJpaRepository.save(comment)
    }

    override fun delete(comment: Comment) {
        commentJpaRepository.delete(comment)
    }

    override fun deleteByIdAndReviewId(commentId: Long, reviewId: Long) {
        commentJpaRepository.deleteByIdAndReviewId(commentId, reviewId)
    }
}