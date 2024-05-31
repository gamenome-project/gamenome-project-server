package sparta.nbcamp.gamenomeprojectserver.domain.review.controller.v1

import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sparta.nbcamp.gamenomeprojectserver.domain.follow.dto.v1.FollowingResponseDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.*
import sparta.nbcamp.gamenomeprojectserver.domain.review.etc.ReviewSort
import sparta.nbcamp.gamenomeprojectserver.domain.review.etc.setSortType
import sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1.ReviewService

@RestController
@RequestMapping("/api/v1/reviews")
class ReviewController(
    private val reviewService: ReviewService
){

    @PostMapping
    fun createReview(
        @RequestBody reviewCreateDTO: ReviewCreateDto,
        request: HttpServletRequest
    ): ResponseEntity<ReviewDto> {


        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(reviewService.createReview(reviewCreateDTO))

    }

    @GetMapping()
    fun getReviewList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "CreatedAtAsc") sort: ReviewSort
    ): ResponseEntity<Page<ReviewDto>> {
        val pageable: Pageable = PageRequest.of(page, size, sort.setSortType())


        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReviewPage(pageable))
    }

    @PutMapping("/{reviewId}")
    fun updateReview(
        @PathVariable reviewId: Long,
        @RequestBody reviewUpdateDTO: ReviewUpdateDto,
        request : HttpServletRequest
    ): ResponseEntity<ReviewDto> {

        val token = request.getHeader("Authorization") ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.updateReview(reviewId, reviewUpdateDTO, token))
    }

    @DeleteMapping("/{reviewId}")
    fun deleteReview(
        @PathVariable reviewId: Long,
    ): ResponseEntity<Unit> {
        reviewService.deleteReview(reviewId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
    }

    @GetMapping("/{reviewId}")
    fun getReview(
        @PathVariable reviewId: Long
    ): ResponseEntity<GetReviewDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReview(reviewId))
    }

    @PostMapping("/{reviewId}/report")
    fun reportReview(
        @PathVariable reviewId: Long,
        @RequestBody reviewReportDTO: ReviewReportDto,
    ): ResponseEntity<ReviewDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(reviewService.reportReview(reviewId, reviewReportDTO))
    }

    @GetMapping("/report")
    fun getReviewReport(): ResponseEntity<List<ReviewDto>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReviewReport())
    }

    @GetMapping
    fun getFollowingUserReviewList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "CreatedAtAsc") sort: ReviewSort,
        request : HttpServletRequest
    ):ResponseEntity<Page<ReviewDto>>{

        val pageable: Pageable = PageRequest.of(page, size, sort.setSortType())

        val token = request.getHeader("Authorization") ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getFollowingReviewPage(token, pageable))
    }
}