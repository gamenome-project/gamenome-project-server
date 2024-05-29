package sparta.nbcamp.gamenomeprojectserver.domain.comment.controller.v1

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sparta.nbcamp.gamenomeprojectserver.domain.comment.dto.v1.*
import sparta.nbcamp.gamenomeprojectserver.domain.comment.etc.CommentSort
import sparta.nbcamp.gamenomeprojectserver.domain.comment.etc.setSortType
import sparta.nbcamp.gamenomeprojectserver.domain.comment.service.v1.CommentService

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
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(defaultValue = "createdAt") sort: CommentSort
        ): ResponseEntity<Page<CommentResponseDto>>{
        val pageable: Pageable = PageRequest.of(0, 10, sort.setSortType())

       return ResponseEntity
           .status(HttpStatus.OK)
           .body(commentService.getCommentPage(reviewId, pageable))
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
        @RequestParam token: String,
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

    @PostMapping("/{commentId}/like")
    fun commentLikeReaction(
        @PathVariable reviewId: Long,
        @PathVariable commentId: Long,
        @RequestParam token: String,
    ):ResponseEntity<Unit>{

        commentService.commentLikeReaction(reviewId, commentId, token)

        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @PostMapping("/{commentId}/dislike")
    fun commentDisLikeReaction(
        @PathVariable reviewId: Long,
        @PathVariable commentId: Long,
        @RequestParam token: String,
    ):ResponseEntity<Unit>{

        commentService.commentDisLikeReaction(reviewId, commentId, token)

        return ResponseEntity.status(HttpStatus.OK).build()
    }


}