package sparta.nbcamp.gamenomeprojectserver.domain.comment.etc

import org.springframework.data.domain.Sort


enum class CommentSort {
    Asc,
    Desc,
}

fun CommentSort.setSortType(): Sort{
    return when(this){
        CommentSort.Asc -> return Sort.by("created_at").ascending()
        CommentSort.Desc -> return Sort.by("created_at").descending()
    }
}