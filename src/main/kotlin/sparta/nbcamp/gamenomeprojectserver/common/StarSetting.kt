package sparta.nbcamp.gamenomeprojectserver.common

import sparta.nbcamp.gamenomeprojectserver.exception.ModelNotFoundException

fun setStarScore(star: Float): Float{
    return when(star){
        in(0.0f..< 0.5f) -> 0.0f
        in(0.5f..< 1.0f) -> 0.5f
        in(1.0f..< 1.5f) -> 1.0f
        in(1.5f..< 2.0f) -> 1.5f
        in(2.0f..< 2.5f) -> 2.0f
        in(2.5f..< 3.0f) -> 2.5f
        in(3.0f..< 3.5f) -> 3.0f
        in(3.5f..< 4.0f) -> 3.5f
        in(4.0f..< 4.5f) -> 4.0f
        in(4.5f..< 5.0f) -> 4.5f
        5.0f -> 5.0f
        else -> throw ModelNotFoundException("Score", 404)
    }
}