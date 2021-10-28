package ma.worldaudit.achat.api.model

import ma.worldaudit.achat.domain.model.ContactDomain
import java.util.*


data class ContactApi(
    var id : Long,
    val nom: String?,
    val telephone: String?,
    val email: String?,
    var supplier: SupplierApi?,
    var createdDate: Date?,
    var modifiedDate: Date?,
    var createdBy: String?,
    var modifiedBy: String?
)


fun ContactApi.toDomain() = ContactDomain(
    id = id,
    nom = nom,
    telephone = telephone,
    email = email,
    createdDate = createdDate,
    modifiedDate = modifiedDate,
    createdBy = createdBy,
    modifiedBy = modifiedBy,
    supplier = supplier!!.toDomain()
)

fun List<ContactApi>.toDomain() = map { it.toDomain() }
