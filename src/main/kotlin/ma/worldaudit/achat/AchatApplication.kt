package ma.worldaudit.achat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class AchatApplication

fun main(args: Array<String>) {
    runApplication<AchatApplication>(*args)
}
