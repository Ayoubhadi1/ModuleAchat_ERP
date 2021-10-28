package ma.worldaudit.achat.domain.model

import ma.worldaudit.achat.api.model.FixedAssetApi
import ma.worldaudit.achat.infra.model.FixedAsset
import java.util.*

data class FixedAssetDomain(
    var id : Long,
    val purchaseDate : Date,
    val commissioningDate : Date,
    var cessionDate : Date,
    val socialReasonSupplier : String,
    val billNumber: String,
    val libelle : String,
    val amountTTC : Double,
    val amountHT : Double,
    val amountTVA : Double,
    val tva : Int,
    val durationOfUse : Int // counting weeks !!
)

fun FixedAssetDomain.toApi() = FixedAssetApi(
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

fun FixedAssetDomain.toInfra() = FixedAsset(
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
