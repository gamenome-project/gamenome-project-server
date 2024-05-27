package sparta.nbcamp.gamenomeprojectserver.exception

class ModelNotFoundException(modelName: String, id: Long) : RuntimeException(
    "Model $modelName with given id $id not found"
)