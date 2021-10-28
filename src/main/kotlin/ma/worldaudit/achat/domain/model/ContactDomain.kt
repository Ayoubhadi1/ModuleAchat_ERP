package ma.worldaudit.achat.domain.model

import ma.worldaudit.achat.api.model.ContactApi
import ma.worldaudit.achat.infra.model.Contact
import java.util.*

data class ContactDomain(
    var id : Long,
    val nom: String?,
    val telephone: String?,
    val email: String?,
    var supplier: SupplierDomain?,
    var createdDate: Date?,
    var modifiedDate: Date?,
    var createdBy: String?,
    var modifiedBy: String?
)

fun ContactDomain.toInfra() = Contact(
    id = id,
    nom = nom,
    telephone = telephone,
    email = email,
    createdDate = createdDate,
    modifiedDate = modifiedDate,
    createdBy = createdBy,
    modifiedBy = modifiedBy,
    supplier = supplier!!.toInfra()
)

fun ContactDomain.toApi() = ContactApi(
    id = id,
    nom = nom,
    telephone = telephone,
    email = email,
    createdDate = createdDate,
    modifiedDate = modifiedDate,
    createdBy = createdBy,
    modifiedBy = modifiedBy,
    supplier = supplier!!.toApi()
)

fun List<ContactDomain>.toInfra() = map { it.toInfra() }

fun List<ContactDomain>.toApi() = map { it.toApi() }
