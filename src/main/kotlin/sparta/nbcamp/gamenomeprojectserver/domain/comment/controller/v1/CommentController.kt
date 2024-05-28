package sparta.nbcamp.gamenomeprojectserver.domain.comment.controller.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sparta.nbcamp.gamenomeprojectserver.domain.ApiV1MappingConfig
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1.*
import sparta.nbcamp.gamenomeprojectserver.domain.comment.service.v1.CommentService

@RestController
@RequestMapping("/api/v1/reviews/{reviewId}/comments")
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

    @PostMapping("/{commentId}/report")
    fun reportComment(
        @PathVariable reviewId: Long,
        @PathVariable commentId: Long,
        @RequestBody reportCommentRequestDto: ReportCommentRequestDto,
    ):ResponseEntity<CommentReportResponseDto>{

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createReportComment(reviewId, commentId, reportCommentRequestDto))
    }


}