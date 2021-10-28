package ma.worldaudit.achat.api.supplier.contactExceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ContactExceptionController {
    @ExceptionHandler(value = [ContactNotFoundException::class])
    fun handleContactNotFoundException(exception: ContactNotFoundException):ResponseEntity<Any>{
        return ResponseEntity("Contact inexistant", HttpStatus.NOT_FOUND)
    }

}