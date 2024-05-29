package sparta.nbcamp.gamenomeprojectserver.domain.comment.etc

import org.springframework.data.domain.Sort


enum class CommentSort {
    CreateAtAsc,
    CreateAtDesc,
}

fun CommentSort.setSortType(): Sort{
    return when(this){
        CommentSort.CreateAtAsc -> Sort.by("created_at").ascending()
        CommentSort.CreateAtDesc -> Sort.by("created_at").descending()
    }
}