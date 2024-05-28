package sparta.nbcamp.gamenomeprojectserver.domain.review.controller.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sparta.nbcamp.gamenomeprojectserver.domain.ApiV1MappingConfig
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewCreateDTO
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewDTO
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewReportDTO
import sparta.nbcamp.gamenomeprojectserver.domain.review.dto.v1.ReviewUpdateDTO
import sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1.ReviewService

@RestController
@RequestMapping("/reviews")
class ReviewController(
    private val reviewService: ReviewService
) : ApiV1MappingConfig() {

    @PostMapping
    fun createReview(@RequestBody reviewCreateDTO: ReviewCreateDTO): ResponseEntity<ReviewDTO> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(reviewService.createReview(reviewCreateDTO))

    }

    @GetMapping()
    fun getReviewList(): ResponseEntity<List<ReviewDTO>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReviewList())
    }

    @PutMapping("/{reviewId}")
    fun updateReview(
        @PathVariable reviewId: Long,
        @RequestBody reviewUpdateDTO: ReviewUpdateDTO
    ): ResponseEntity<ReviewDTO> {

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
    ): ResponseEntity<ReviewDTO> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReview(reviewId))
    }

    @PostMapping("/{reviewId}/report")
    fun reportReview(
        @PathVariable reviewId: Long,
        @RequestBody reviewReportDTO: ReviewReportDTO,
    ): ResponseEntity<ReviewDTO> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(reviewService.reportReview(reviewId, reviewReportDTO))
    }

    @GetMapping("/report")
    fun getReviewReport(): ResponseEntity<List<ReviewDTO>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(reviewService.getReviewReport())
    }
}