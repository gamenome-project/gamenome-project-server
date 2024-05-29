package sparta.nbcamp.gamenomeprojectserver.domain.review.etc

import org.springframework.data.domain.Sort


enum class ReviewSort {
    CreatedAtAsc,
    CreatedAtDesc,
}

fun ReviewSort.setSortType():Sort{
    return when(this){
        ReviewSort.CreatedAtAsc -> Sort.by("createAt").ascending()
        ReviewSort.CreatedAtDesc -> Sort.by("createAt").descending()
    }
}