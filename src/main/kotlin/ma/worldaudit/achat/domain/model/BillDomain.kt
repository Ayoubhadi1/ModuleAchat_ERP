package ma.worldaudit.achat.domain.model

import ma.worldaudit.achat.api.model.BillApi
import ma.worldaudit.achat.infra.model.Bill
import java.util.*

data class BillDomain(
    var id: Long,
    var supplier: SupplierDomain?,
    var refFacture: String?,
    var dateFacture: Date?,
    var refPaiement: String?,
    var articles: List<ItemDomain>?,
    var dateEcheance: Date?,
    var createdDate: Date?,
    var modifiedDate: Date?,
    var createdBy: String?,
    var modifiedBy: String?
)

fun BillDomain.toInfra() = Bill(
    id = id,
    items = articles!!.toInfra(),
    dateFacture = dateFacture,
    refFacture = refFacture,
    createdBy = createdBy,
    createdDate = createdDate,
    dateEcheance = dateEcheance,
    supplier = supplier!!.toInfra(),
    modifiedBy = modifiedBy,
    modifiedDate = modifiedDate,
    refPaiement = refPaiement
)

fun BillDomain.toApi() = BillApi(
    id = id,
    articles = articles!!.toApi(),
    dateFacture = dateFacture,
    refFacture = refFacture,
    createdBy = createdBy,
    createdDate = createdDate,
    dateEcheance = dateEcheance,
    supplier = supplier!!.toApi(),
    modifiedBy = modifiedBy,
    modifiedDate = modifiedDate,
    refPaiement = refPaiement
)

fun List<BillDomain>.toApi() = map { it.toApi() }
