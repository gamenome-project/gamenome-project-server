package sparta.nbcamp.gamenomeprojectserver.domain.comment.controller.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1.*
import sparta.nbcamp.gamenomeprojectserver.domain.comment.service.v1.CommentService
import sparta.nbcamp.gamenomeprojectserver.domain.report.dto.v1.ReportReviewDto

@RestController
@RequestMapping("/api/v1/reviews/{reviewId}/comments")
class CommentController(
    private val commentService: CommentService
) {

    @PostMapping
    fun createComment(
        @PathVariable reviewId: Long,
        @RequestBody createCommentRequestDto: CreateCommentRequestDto,
        @RequestParam token: String,
    ): ResponseEntity<CommentResponseDto>{

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(reviewId, createCommentRequestDto, token))
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
        @RequestBody updateCommentRequestDto: UpdateCommentRequestDto,
        @RequestHeader("Authorization") token: String,
    ):ResponseEntity<CommentResponseDto>{

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(reviewId, commentId, updateCommentRequestDto))
    }

    @PostMapping("/{commentId}/report")
    fun reportComment(
        @PathVariable reviewId: Long,
        @PathVariable commentId: Long,
        @RequestBody reportReviewDto: ReportReviewDto,
    ):ResponseEntity<CommentReportResponseDto>{

        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createReportComment(reviewId, commentId, reportReviewDto))
    }

    @PostMapping("/{commentId}/like")
    fun commentLikeReaction(
        @PathVariable reviewId: Long,
        @PathVariable commentId: Long,
        @RequestParam token: String,
    ):ResponseEntity<Unit>{

        commentService.commentLikeReaction(reviewId, commentId)

        return ResponseEntity.status(HttpStatus.OK).build()
    }


}