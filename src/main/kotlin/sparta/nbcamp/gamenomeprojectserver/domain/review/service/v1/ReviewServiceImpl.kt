package sparta.nbcamp.gamenomeprojectserver.domain.review.service.v1

import org.springframework.stereotype.Service
import sparta.nbcamp.gamenomeprojectserver.domain.review.repository.v1.ReviewRepository

@Service
class ReviewServiceImpl(
    private val reviewRepository: ReviewRepository
) : ReviewService {

}