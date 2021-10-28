package ma.worldaudit.achat.api.model

import ma.worldaudit.achat.domain.model.BillDomain
import java.util.*

data class BillApi(
    var id : Long,
    var supplier: SupplierApi?,
    var refFacture: String?,
    var articles : List<ItemApi>?,
    var dateFacture: Date?,
    var refPaiement: String?,
    var dateEcheance: Date?,
    var createdDate: Date?,
    var modifiedDate: Date?,
    var createdBy: String?,
    var modifiedBy: String?
)

fun BillApi.toDomain() = BillDomain(
    id = id,
    articles = articles!!.toDomain(),
    dateFacture = dateFacture,
    refFacture = refFacture,
    createdBy = createdBy,
    createdDate = createdDate,
    dateEcheance = dateEcheance,
    supplier = supplier!!.toDomain(),
    modifiedBy = modifiedBy,
    modifiedDate = modifiedDate,
    refPaiement = refPaiement
)
