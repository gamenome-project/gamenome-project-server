package sparta.nbcamp.gamenomeprojectserver.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import sparta.nbcamp.gamenomeprojectserver.exception.dto.ErrorDto

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorDto> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto(e.message, "100"))
    }

    @ExceptionHandler(DescriptionNotFoundException::class)
    fun descriptionNotFoundException(e: DescriptionNotFoundException): ResponseEntity<ErrorDto>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorDto(e.message, "404"))
    }
}