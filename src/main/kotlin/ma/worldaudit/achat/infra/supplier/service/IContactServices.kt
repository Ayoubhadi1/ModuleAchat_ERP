package ma.worldaudit.achat.infra.supplier.service

import ma.worldaudit.achat.infra.model.Supplier
import ma.worldaudit.achat.infra.model.Contact

interface IContactServices {
    fun getAll(): List<Contact>
    fun save(contact: Contact)
    fun getById(personneId: Long): Contact
    fun deleteById(personneId: Long)
    fun getByFournisseur(supplier: Supplier):List<Contact>
}