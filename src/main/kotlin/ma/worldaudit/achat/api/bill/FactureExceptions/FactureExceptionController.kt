package ma.worldaudit.achat.api.bill.FactureExceptions

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class FactureExceptionController {

    @ExceptionHandler(value = [FactureNotFoundException::class])//404
    fun handleFactureNotFoundException(exception: FactureNotFoundException?): ResponseEntity<Any> {
        return ResponseEntity("Facture inexistante", HttpStatus.NOT_FOUND)
    }

}