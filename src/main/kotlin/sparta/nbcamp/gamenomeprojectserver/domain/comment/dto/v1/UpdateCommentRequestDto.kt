package sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1

import sparta.nbcamp.gamenomeprojectserver.domain.comment.entity.v1.Comment

data class UpdateCommentRequestDto(
    val content: String,
){
    companion object{
        fun update(comment: Comment): UpdateCommentRequestDto {
            return UpdateCommentRequestDto(
                content = comment.content,
            )
        }
    }
}