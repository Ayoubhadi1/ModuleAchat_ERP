package ma.worldaudit.achat.infra.supplier.repository

import ma.worldaudit.achat.infra.model.Supplier
import ma.worldaudit.achat.infra.model.Contact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IContactRepository : JpaRepository<Contact, Long> {
    fun findBySupplier(supplier:Supplier):List<Contact>
}