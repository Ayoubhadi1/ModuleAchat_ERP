package ma.worldaudit.achat.api.bill.ecritureComptableExceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException


class EcritureComptableNotFoundException : RuntimeException() {

}