package ma.worldaudit.achat.infra.bill.repository
import ma.worldaudit.achat.infra.model.Bill
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IBillRepository : JpaRepository<Bill, Long> {
    override fun findAll(pageable: Pageable): Page<Bill>
    override fun findAll(): List<Bill>
}
