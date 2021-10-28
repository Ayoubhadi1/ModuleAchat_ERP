package ma.worldaudit.achat.api.supplier.adapter

import ma.worldaudit.achat.api.supplier.FournisseurExceptions.FournisseurNotFoundException
import ma.worldaudit.achat.api.model.SupplierApi
import ma.worldaudit.achat.api.model.ContactApi
import ma.worldaudit.achat.api.model.toDomain
import ma.worldaudit.achat.domain.model.toApi

import ma.worldaudit.achat.domain.port.api.ISupplierServicesPort

import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
class SupplierController(
    private val iSupplierService: ISupplierServicesPort,
) {
    @PostMapping("/suppliers/")
    fun addSupplier(@RequestBody supllier: SupplierApi) {
        iSupplierService.addSupplier(supllier.toDomain())
    }

    @GetMapping("/suppliers/{id}")
    fun getSupplier(@PathVariable id: Long): SupplierApi {
        try {
            return iSupplierService.getSupplier(id).toApi()
        } catch (e: FournisseurNotFoundException) {
            throw FournisseurNotFoundException()
        }
    }

    @PutMapping("/suppliers/{id}/update")
    fun updateSupllier(@PathVariable id: Long, @RequestBody supplier: SupplierApi) {
        try {
            iSupplierService.updateSupplier(id, supplier.toDomain())
        } catch (e: FournisseurNotFoundException) {
            throw FournisseurNotFoundException()
        }
    }

    @DeleteMapping("/suppliers/{id}")
    fun deleteSupplier(@PathVariable id: Long) {
        try {
            iSupplierService.deleteSupplier(id)
        } catch (e: FournisseurNotFoundException) {
            throw FournisseurNotFoundException()

        }
    }
    @GetMapping("/suppliers/mails/{mail}")
    fun getSupplierByEmail(@PathVariable mail: String): SupplierApi {
        return iSupplierService.getSupplierByEmail(mail).toApi()
    }

    @GetMapping("/suppliers/{id}/contacts")
    fun getSupplierContact(@PathVariable id: Long): List<ContactApi> {
        return iSupplierService.getSupplierContacts(id).toApi()
    }

    @GetMapping("/suppliers/{id}/emails")
    fun getEmails(@PathVariable id: Long): List<String> {
        return iSupplierService.getContactEmails(id)
    }

    @GetMapping("/suppliers/{id}/phones")
    fun getPhoneContact(@PathVariable id: Long): List<String> {
        return iSupplierService.getContactPhoneNumbers(id)
    }

    @PostMapping("/Contacts/{id}")
    fun addContact(@RequestBody contact: ContactApi) {
        iSupplierService.addContactToSupplier(contact.toDomain())
    }

    @DeleteMapping("/Contacts/{contactId}/")
    fun deleteContact( @PathVariable contactId: Long) {
        iSupplierService.deleteContact( contactId)
    }

    @PutMapping("/Contacts/{contactId}/")
    fun updateContact(@PathVariable contactId: Long,@RequestBody contact: ContactApi) {
        iSupplierService.updateContact(contactId,contact.toDomain())
    }
    @GetMapping("/suppliers")
    fun getAllSuppliers(@RequestParam(name = "page", defaultValue = "0") page: Int,
                        @RequestParam(name = "size", defaultValue = "10") size: Int
    ) : List<SupplierApi>{
        return iSupplierService.getAllSuppliers(page, size).toApi()
    }

    @GetMapping("/Contacts")
    fun getAllContacts() : List<ContactApi>{
        return iSupplierService.getAllContacts().toApi()
    }
}
