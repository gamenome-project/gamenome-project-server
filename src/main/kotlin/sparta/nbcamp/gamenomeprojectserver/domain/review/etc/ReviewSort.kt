package sparta.nbcamp.gamenomeprojectserver.domain.review.etc

import org.springframework.data.domain.Sort


enum class ReviewSort {
    CreatedAtAsc,
    CreatedAtDesc,
    GameNameAsc,
    GameNameDesc,
    TitleAsc,
    TitleDesc,
}

fun ReviewSort.setSortType():Sort{
    return when(this){
        ReviewSort.CreatedAtAsc -> Sort.by("createdAt").ascending()
        ReviewSort.CreatedAtDesc -> Sort.by("createdAt").descending()
        ReviewSort.GameNameAsc -> Sort.by("gameName").ascending()
        ReviewSort.GameNameDesc -> Sort.by("gameName").descending()
        ReviewSort.TitleAsc -> Sort.by("title").ascending()
        ReviewSort.TitleDesc -> Sort.by("title").descending()
    }
}