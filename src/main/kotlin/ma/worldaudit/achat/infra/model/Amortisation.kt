package ma.worldaudit.achat.infra.model

import ma.worldaudit.achat.domain.model.AmortisationDomain
import java.util.*
import javax.persistence.*

@Entity
@Table
class Amortisation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val amortizationDate: Date? = null,
    @ManyToOne(cascade = [CascadeType.ALL])
    val fixedAsset: FixedAsset? = null,
    val depreciationRate: Double? = null,
    val cumulativeDepreciationN_1: Double? = null,
    val depreciationExpense: Double? = null, // dotation aux amortissements
    val cumulativeDepreciationN: Double? = null,
    val netValue: Double? = null, //valeur Nette Comptable
    val releasedate: Date? = null,
    val outputType: String? = null,
    val salePrice: Double? = null
)

fun Amortisation.toDomain() = AmortisationDomain(
    id = id,
    amortizationDate = amortizationDate,
    fixedAsset = fixedAsset!!.toDomain(),
    depreciationRate = depreciationRate,
    cumulativeDepreciationN_1 = cumulativeDepreciationN_1,
    depreciationExpense = depreciationExpense,
    cumulativeDepreciationN = cumulativeDepreciationN,
    netValue = netValue,
    releasedate = releasedate,
    outputType = outputType,
    salePrice = salePrice
)
