package sparta.nbcamp.gamenomeprojectserver.domain.comment.repository

import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.Comment


interface CommentRepository: JpaRepository<Comment, Long> {
}