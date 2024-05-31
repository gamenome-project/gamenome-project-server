package sparta.nbcamp.gamenomeprojectserver.exception

data class FileValidateException(
    val msg: String,
):RuntimeException(msg)

