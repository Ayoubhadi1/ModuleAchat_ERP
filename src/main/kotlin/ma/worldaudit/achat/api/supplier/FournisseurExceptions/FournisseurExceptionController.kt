package ma.worldaudit.achat.api.supplier.FournisseurExceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class FournisseurExceptionController {

    @ExceptionHandler(value = [FournisseurNotFoundException::class])
    fun handleFournisseurNotFoundException(exception: FournisseurNotFoundException):ResponseEntity<Any>{
        return ResponseEntity("Supplier inexistant", HttpStatus.NOT_FOUND)
    }
}