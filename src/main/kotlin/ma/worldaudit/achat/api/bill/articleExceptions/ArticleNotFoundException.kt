package ma.worldaudit.achat.api.bill.articleExceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException


class ArticleNotFoundException : RuntimeException() {

}