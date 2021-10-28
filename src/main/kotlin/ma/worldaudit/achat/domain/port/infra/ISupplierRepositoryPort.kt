package ma.worldaudit.achat.domain.port.infra

import ma.worldaudit.achat.domain.model.SupplierDomain
import ma.worldaudit.achat.domain.model.ContactDomain

interface ISupplierRepositoryPort {
    fun addSupplier(supplierDomain: SupplierDomain)
    fun updateSupplier(id:Long, supplierDomainNew: SupplierDomain)
    fun deleteSupplier(id:Long)
    fun getSupplier(id:Long): SupplierDomain
    fun getSupplierByEmail(email:String) : SupplierDomain
    fun getSupplierContact(idFournisseur: Long): List<ContactDomain>
    fun getContactPhoneNumbers(idFournisseur: Long) : List<String>
    fun getContactEmails(idFournisseur: Long) : List<String>
    fun addContactToSupplier(contactDomain: ContactDomain)
    fun deleteContact(idPersonneDomain: Long)
    fun updateContact(personneDomainId: Long, contactDomain: ContactDomain )
    fun getAllSuppliers(page: Int, size: Int): List<SupplierDomain>
    fun getAllSuppliers(): List<SupplierDomain>
    fun getAllContacts(): List<ContactDomain>
}
