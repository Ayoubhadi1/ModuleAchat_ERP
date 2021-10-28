package ma.worldaudit.achat.infra.model

import ma.worldaudit.achat.domain.model.FixedAssetDomain
import java.util.*
import javax.persistence.*

@Entity
@Table
class FixedAsset(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long?=null,
    val purchaseDate : Date?=null,
    val commissioningDate : Date?=null,
    val cessionDate : Date?=null,
    val socialReasonSupplier : String?=null,
    val billNumber: String?=null,
    val libelle : String?=null,
    val amountTTC : Double?=null,
    val amountHT : Double?=null,
    val amountTVA : Double?=null,
    val tva : Int ?=null,
    val durationOfUse : Int?=null // counting weeks !!
)

fun FixedAsset.toDomain() = FixedAssetDomain(
    id = id!!,
    purchaseDate = purchaseDate!!,
    commissioningDate = commissioningDate!!,
    cessionDate = cessionDate!!,
    socialReasonSupplier = socialReasonSupplier!!,
    billNumber = billNumber!!,
    libelle = libelle!!,
    amountTTC = amountTTC!!,
    amountHT = amountHT!!,
    amountTVA = amountTVA!!,
    durationOfUse = durationOfUse!!,
    tva = tva!!
)
