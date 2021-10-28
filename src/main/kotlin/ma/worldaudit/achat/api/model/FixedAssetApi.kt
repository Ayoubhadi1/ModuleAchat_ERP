package ma.worldaudit.achat.api.model

import ma.worldaudit.achat.domain.model.FixedAssetDomain
import java.util.*

data class FixedAssetApi(
    val id : Long,
    val purchaseDate : Date,
    val commissioningDate : Date,
    val cessionDate : Date,
    val socialReasonSupplier : String,
    val billNumber: String,
    val libelle : String,
    val amountTTC : Double,
    val amountHT : Double,
    val amountTVA : Double,
    val tva : Int,
    val durationOfUse : Int // counting weeks !!
)

fun FixedAssetApi.toDomain() = FixedAssetDomain(
    id = id,
    purchaseDate = purchaseDate,
    commissioningDate = commissioningDate,
    cessionDate = cessionDate,
    socialReasonSupplier = socialReasonSupplier,
    billNumber = billNumber,
    libelle = libelle,
    amountTTC = amountTTC,
    amountHT = amountHT,
    amountTVA = amountTVA,
    durationOfUse = durationOfUse,
    tva = tva
)
