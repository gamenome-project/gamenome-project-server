package sparta.nbcamp.gamenomeprojectserver.domain.comment.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sparta.nbcamp.gamenomeprojectserver.domain.ApiV1MappingConfig
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.CommentResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.CreateCommentRequestDto
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.UpdateCommentRequestDto
import sparta.nbcamp.gamenomeprojectserver.domain.comment.service.CommentService

@RestController
@RequestMapping("/reviews/{reviewId}/comments")
class CommentController(
    private val commentService: CommentService
):ApiV1MappingConfig() {

    @PostMapping
    fun createComment(
        @PathVariable reviewId: Long,
        @RequestBody createCommentRequestDto: CreateCommentRequestDto,
    ): ResponseEntity<CommentResponseDto>{

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(reviewId, createCommentRequestDto))
    }

    @GetMapping
    fun getCommentList(
        @PathVariable reviewId: Long,
        ): ResponseEntity<List<CommentResponseDto>>{

       return ResponseEntity
           .status(HttpStatus.OK)
           .body(commentService.getCommentList(reviewId))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable reviewId: Long,
        @PathVariable commentId: Long,
    ):ResponseEntity<Unit>{

        commentService.deleteComment(reviewId, commentId)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable reviewId: Long,
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequestDto: UpdateCommentRequestDto
    ):ResponseEntity<CommentResponseDto>{

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(reviewId, commentId, updateCommentRequestDto))
    }

}