package ma.worldaudit.achat.api.bill.ecritureComptableExceptions

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class EcritureComptableExceptionController {

    @ExceptionHandler(value = [EcritureComptableNotFoundException::class])//404
    fun handleECNotFoundException(exception: EcritureComptableNotFoundException?): ResponseEntity<Any> {
        return ResponseEntity("Ecriture comptable inexistante", HttpStatus.NOT_FOUND)
    }

}