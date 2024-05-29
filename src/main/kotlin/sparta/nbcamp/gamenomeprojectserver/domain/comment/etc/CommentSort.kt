package sparta.nbcamp.gamenomeprojectserver.domain.comment.etc

import org.springframework.data.domain.Sort


enum class CommentSort {
    CreatedAtAsc,
    CreateAtDesc,
}

fun CommentSort.setSortType(): Sort{
    return when(this){
        CommentSort.CreatedAtAsc -> Sort.by("createdAt").ascending()
        CommentSort.CreateAtDesc -> Sort.by("createdAt").descending()
    }
}