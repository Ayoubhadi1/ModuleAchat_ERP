package ma.worldaudit.achat.api.bill.articleExceptions

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ArticleExceptionController {

    @ExceptionHandler(value = [ArticleNotFoundException::class])//404
    fun handleArticleNotFoundException(exception: ArticleNotFoundException?): ResponseEntity<Any> {
        return ResponseEntity("Article inexistante", HttpStatus.NOT_FOUND)
    }




}