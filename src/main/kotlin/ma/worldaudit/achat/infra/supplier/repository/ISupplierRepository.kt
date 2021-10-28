package ma.worldaudit.achat.infra.supplier.repository

import ma.worldaudit.achat.infra.model.Supplier
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ISupplierRepository : JpaRepository<Supplier, Long> {
    fun findByMail(mail:String): Supplier
    override fun findAll(pageable: Pageable): Page<Supplier>
    override fun findAll(): List<Supplier>
}
