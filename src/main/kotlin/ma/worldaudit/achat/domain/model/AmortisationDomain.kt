package ma.worldaudit.achat.domain.model

import ma.worldaudit.achat.api.model.AmortisationApi
import ma.worldaudit.achat.infra.model.Amortisation
import ma.worldaudit.achat.infra.model.FixedAsset
import java.util.*
import javax.persistence.*

data class AmortisationDomain(
    var id: Long? = null,
    val amortizationDate: Date? = null,
    val fixedAsset: FixedAssetDomain? = null,
    val depreciationRate: Double? = null,
    val cumulativeDepreciationN_1: Double? = null,
    var depreciationExpense: Double? = null, // dotation aux amortissements
    val cumulativeDepreciationN: Double? = null,
    val netValue: Double? = null, //valeur Nette Comptable
    var releasedate: Date? = null,
    var outputType: String? = null,
    var salePrice: Double? = null
)

fun AmortisationDomain.toApi() = AmortisationApi(
    id = id,
    amortizationDate = amortizationDate,
    fixedAsset = fixedAsset!!.toApi(),
    depreciationRate = depreciationRate,
    cumulativeDepreciationN_1 = cumulativeDepreciationN_1,
    depreciationExpense = depreciationExpense,
    cumulativeDepreciationN = cumulativeDepreciationN,
    netValue = netValue,
    releasedate = releasedate,
    outputType = outputType,
    salePrice = salePrice
)

fun AmortisationDomain.toInfra() = Amortisation(
    id = id,
    amortizationDate = amortizationDate,
    fixedAsset = fixedAsset!!.toInfra(),
    depreciationRate = depreciationRate,
    cumulativeDepreciationN_1 = cumulativeDepreciationN_1,
    depreciationExpense = depreciationExpense,
    cumulativeDepreciationN = cumulativeDepreciationN,
    netValue = netValue,
    releasedate = releasedate,
    outputType = outputType,
    salePrice = salePrice
)
