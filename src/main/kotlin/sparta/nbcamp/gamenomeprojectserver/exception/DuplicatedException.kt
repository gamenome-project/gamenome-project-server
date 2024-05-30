package sparta.nbcamp.gamenomeprojectserver.exception

data class DuplicatedException(
    val msg: String,
):RuntimeException(msg)