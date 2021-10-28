package ma.worldaudit.achat.domain.usecase.supplier

import ma.worldaudit.achat.api.supplier.contactExceptions.ContactNotFoundException
import ma.worldaudit.achat.api.supplier.FournisseurExceptions.FournisseurNotFoundException
import ma.worldaudit.achat.domain.model.SupplierDomain
import ma.worldaudit.achat.domain.model.ContactDomain
import ma.worldaudit.achat.domain.port.api.ISupplierServicesPort
import ma.worldaudit.achat.domain.port.infra.ISupplierRepositoryPort

class UseCaseSupplier(
    private val fournisseurRepository: ISupplierRepositoryPort
) : ISupplierServicesPort {
    fun fournisseurPresent(idFournisseur : Long):Boolean{
        fournisseurRepository.getAllSuppliers().map {
            if(it.id == idFournisseur) return true
        }
        return false
    }
    fun fournisseurPresentByEmal(emailFournisseur : String):Boolean{
        fournisseurRepository.getAllSuppliers().map {
            if(it.mail.equals(emailFournisseur)) return true
        }
        return false
    }
    fun contactPresent(idContact : Long):Boolean{
        getAllContacts().map {
            if(it.id == idContact) return true
        }
        return false
    }

    override fun addSupplier(supplierDomain: SupplierDomain) {
        fournisseurRepository.addSupplier(supplierDomain)
    }

    override fun updateSupplier(id: Long, supplierDomainNew: SupplierDomain) {
        if(!fournisseurPresent(id)) throw FournisseurNotFoundException()
        fournisseurRepository.updateSupplier(id, supplierDomainNew)
    }

    override fun deleteSupplier(id: Long) {
        if(!fournisseurPresent(id)) throw FournisseurNotFoundException()
        //A revoir: if(fournisseur is deleted) ==> facture.fournisseur?? :D
        fournisseurRepository.deleteSupplier(id)
    }

    override fun getSupplier(id: Long): SupplierDomain {
        if(!fournisseurPresent(id)) throw FournisseurNotFoundException()
        return fournisseurRepository.getSupplier(id)
    }

    override fun getSupplierByEmail(email: String): SupplierDomain {
        if(!fournisseurPresentByEmal(email)) throw FournisseurNotFoundException()
        return fournisseurRepository.getSupplierByEmail(email)
    }

    override fun getSupplierContacts(idFournisseur: Long): List<ContactDomain> {
        if(!fournisseurPresent(idFournisseur)) throw FournisseurNotFoundException()
        return fournisseurRepository.getSupplierContact(idFournisseur)
    }

    override fun getContactPhoneNumbers(idFournisseur: Long): List<String> {
        if(!fournisseurPresent(idFournisseur)) throw FournisseurNotFoundException()
        return fournisseurRepository.getContactPhoneNumbers(idFournisseur)
    }

    override fun getContactEmails(idFournisseur: Long): List<String> {
        if(!fournisseurPresent(idFournisseur)) throw FournisseurNotFoundException()
        return fournisseurRepository.getContactEmails(idFournisseur)
    }

    override fun addContactToSupplier(contactDomain: ContactDomain) {
        fournisseurRepository.addContactToSupplier(contactDomain)
    }

    override fun deleteContact(idPersonneDomain: Long) {
        if(!contactPresent(idPersonneDomain)) throw ContactNotFoundException()
        fournisseurRepository.deleteContact(idPersonneDomain)
    }

    override fun updateContact(contactId: Long, contactDomain: ContactDomain ) {
        if(!contactPresent(contactId)) throw ContactNotFoundException()
        fournisseurRepository.updateContact(contactId, contactDomain)
    }

    override fun getAllSuppliers(page: Int, size: Int): List<SupplierDomain> {
        return fournisseurRepository.getAllSuppliers(page, size)
    }

    override fun getAllContacts(): List<ContactDomain> {
        return fournisseurRepository.getAllContacts()
    }

}
