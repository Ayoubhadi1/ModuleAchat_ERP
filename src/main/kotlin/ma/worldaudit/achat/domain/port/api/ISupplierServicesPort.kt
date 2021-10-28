package ma.worldaudit.achat.domain.port.api

import ma.worldaudit.achat.domain.model.SupplierDomain
import ma.worldaudit.achat.domain.model.ContactDomain

interface ISupplierServicesPort {
    fun addSupplier(supplierDomain: SupplierDomain)
    fun updateSupplier(id:Long, supplierDomainNew: SupplierDomain)
    fun deleteSupplier(id:Long)
    fun getSupplier(id:Long): SupplierDomain
    fun getSupplierByEmail(email:String) : SupplierDomain
    fun getSupplierContacts(supplierId:Long) : List<ContactDomain>
    fun getContactPhoneNumbers(supplierId: Long) : List<String>
    fun getContactEmails(supplierId: Long) : List<String>
    fun addContactToSupplier(contactDomain: ContactDomain)
    fun deleteContact(contactId: Long)
    fun updateContact(contactId: Long, contactDomain: ContactDomain)
    fun getAllSuppliers(page: Int, size: Int) : List<SupplierDomain>
    fun getAllContacts(): List<ContactDomain>
}
