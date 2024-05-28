package sparta.nbcamp.gamenomeprojectserver.exception

data class DescriptionNotFoundException(
    val description: String,
):RuntimeException("this $description No blank spaces allowed")