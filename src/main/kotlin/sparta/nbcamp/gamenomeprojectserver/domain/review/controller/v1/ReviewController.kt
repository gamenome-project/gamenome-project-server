package sparta.nbcamp.gamenomeprojectserver.domain.review.controller.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sparta.nbcamp.gamenomeprojectserver.domain.ApiV1MappingConfig
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewCreateDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewReportDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewUpdateDto
import sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1.ReviewService

@RestController
@RequestMapping("/reviews")
class ReviewController(
    private val reviewService: ReviewService
) : ApiV1MappingConfig() {

    @PostMapping
    fun createReview(@RequestBody reviewCreateDTO: ReviewCreateDto): ResponseEntity<ReviewDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(reviewService.createReview(reviewCreateDTO))

    }

    @GetMapping()
    fun getReviewList(): ResponseEntity<List<ReviewDto>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReviewList())
    }

    @PutMapping("/{reviewId}")
    fun updateReview(
        @PathVariable reviewId: Long,
        @RequestBody reviewUpdateDTO: ReviewUpdateDto
    ): ResponseEntity<ReviewDto> {

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.updateReview(reviewId, reviewUpdateDTO))
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
    ): ResponseEntity<ReviewDto> {
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
}