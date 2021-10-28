package ma.worldaudit.achat.infra.supplier.adapter

import ma.worldaudit.achat.domain.model.SupplierDomain
import ma.worldaudit.achat.domain.model.ContactDomain
import ma.worldaudit.achat.domain.model.toInfra
import ma.worldaudit.achat.domain.port.infra.ISupplierRepositoryPort
import ma.worldaudit.achat.infra.supplier.service.ISupplierServices
import ma.worldaudit.achat.infra.supplier.service.impl.ContactService
import ma.worldaudit.achat.infra.model.Contact
import ma.worldaudit.achat.infra.model.Supplier
import ma.worldaudit.achat.infra.model.toDomain
import org.springframework.stereotype.Component

@Component
class SupplierAdapterPort(
    private val iSupplierServices: ISupplierServices,
    private val personneService: ContactService
) : ISupplierRepositoryPort {
    override fun addSupplier(supplierDomain: SupplierDomain) {
        iSupplierServices.save(supplierDomain.toInfra())
    }

    override fun updateSupplier(id: Long, supplierDomainNew: SupplierDomain) {
        val updatedFournisseur = supplierDomainNew.toInfra()
        updatedFournisseur.id = id
        iSupplierServices.save(updatedFournisseur)
    }

    override fun deleteSupplier(id: Long) {
        iSupplierServices.deleteById(id)
    }

    override fun getSupplier(id: Long): SupplierDomain {
        return iSupplierServices.getById(id).toDomain()
    }

    override fun getSupplierByEmail(email: String): SupplierDomain {
        return iSupplierServices.getByEmail(email).toDomain()
    }

    override fun getSupplierContact(idFournisseur: Long): List<ContactDomain> {
        return personneService.getByFournisseur(iSupplierServices.getById(idFournisseur)).toDomain()
    }


    override fun getContactPhoneNumbers(idFournisseur: Long): List<String> {
        val phoneList = mutableListOf<String>()
        getSupplierContact(idFournisseur).map {
            phoneList.add(it.telephone!!)
        }
        return phoneList
    }

    override fun getContactEmails(idFournisseur: Long): List<String> {
        val phoneList = mutableListOf<String>()
        getSupplierContact(idFournisseur).map {
            phoneList.add(it.email!!)
        }
        return phoneList
    }

    override fun addContactToSupplier(contactDomain: ContactDomain) {
        personneService.save(contactDomain.toInfra())
    }

    override fun deleteContact(idPersonneDomain: Long) {
        val contact: Contact = personneService.getById(idPersonneDomain)
        val supplier: Supplier = contact.supplier!!
        var n = 0
        for (it in personneService.getByFournisseur(supplier)) {
            if (it == contact) {
                personneService.getByFournisseur(supplier).drop(n)
                break
            }
            n++
        }
        personneService.deleteById(contact.id!!)
    }

    override fun updateContact(idPersonneDomain: Long, personne: ContactDomain) {
        personne.id=idPersonneDomain
        addContactToSupplier(personne)
    }

    override fun getAllSuppliers(page: Int, size: Int): List<SupplierDomain> {
        return iSupplierServices.getAllFournisseur(page, size).toList().toDomain()
    }

    override fun getAllSuppliers(): List<SupplierDomain> {
        TODO("Not yet implemented")
    }

    override fun getAllContacts(): List<ContactDomain> {
        return personneService.getAll().toDomain()
    }

}
