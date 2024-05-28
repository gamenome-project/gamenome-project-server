package sparta.nbcamp.gamenomeprojectserver.domain.comment.repository.v1

import org.springframework.data.jpa.repository.JpaRepository
import sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.v1.Comment


interface CommentRepository: JpaRepository<Comment, Long> {
}