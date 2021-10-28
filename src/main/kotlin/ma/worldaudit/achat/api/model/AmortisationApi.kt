package ma.worldaudit.achat.api.model

import ma.worldaudit.achat.domain.model.AmortisationDomain
import ma.worldaudit.achat.domain.model.toApi
import ma.worldaudit.achat.infra.model.FixedAsset
import java.util.*
import javax.persistence.*

data class AmortisationApi(
    val id: Long? = null,
    val amortizationDate: Date? = null,
    val fixedAsset: FixedAssetApi? = null,
    val depreciationRate: Double? = null,
    val cumulativeDepreciationN_1: Double? = null,
    val depreciationExpense: Double? = null, // dotation aux amortissements
    val cumulativeDepreciationN: Double? = null,
    val netValue: Double? = null, //valeur Nette Comptable
    val releasedate: Date? = null,
    val outputType: String? = null,
    val salePrice: Double? = null
)

fun AmortisationApi.toDomain() = AmortisationDomain(
    id = id,
    amortizationDate=amortizationDate,
    fixedAsset = fixedAsset!!.toDomain(),
    depreciationRate = depreciationRate,
    cumulativeDepreciationN_1 = cumulativeDepreciationN_1,
    depreciationExpense = depreciationExpense,
    cumulativeDepreciationN = cumulativeDepreciationN,
    netValue = netValue,
    releasedate=releasedate,
    outputType=outputType,
    salePrice=salePrice
)
