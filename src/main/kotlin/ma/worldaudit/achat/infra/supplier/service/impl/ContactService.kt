package ma.worldaudit.achat.infra.supplier.service.impl

import ma.worldaudit.achat.infra.supplier.repository.IContactRepository
import ma.worldaudit.achat.infra.supplier.service.IContactServices
import ma.worldaudit.achat.infra.model.Supplier
import ma.worldaudit.achat.infra.model.Contact
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class ContactService(private val iContactRepository: IContactRepository) : IContactServices {
    override fun getAll(): List<Contact> {
        return iContactRepository.findAll()
    }

    override fun save(contact: Contact) {
        iContactRepository.save(contact)
    }

    override fun getById(personneId: Long): Contact {
        return iContactRepository.findById(personneId).get()
    }

    override fun deleteById(personneId: Long) {
        iContactRepository.delete(getById(personneId))
    }

    override fun getByFournisseur(supplier: Supplier): List<Contact> {
        return iContactRepository.findBySupplier(supplier)
    }
}