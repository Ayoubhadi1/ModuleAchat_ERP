package ma.worldaudit.achat.domain.port.api

import ma.worldaudit.achat.domain.model.AmortisationDomain
import ma.worldaudit.achat.domain.model.BillDomain
import ma.worldaudit.achat.domain.model.FixedAssetDomain

interface IFixedAssetServicesPort {

    fun generateAmortisationEntriesWithProtataTemporis(fixedAsset: FixedAssetDomain)
    fun generateAmortisationEntriesWithoutProtataTemporis(fixedAsset: FixedAssetDomain)
    fun generateAccountingEntry()
    fun generateAccountingEntryCession()
    fun generationCessionAccountingEntriesDepreciationAmortisation(id:Long)
    fun generationCessionAccountingEntriesSale(id:Long)
    fun generationCessionAccountingEntriesReleaseFixedAsset(id:Long)
    fun setCession(id: Long, amortisation: AmortisationDomain)
    fun generateAmortisationWithProtataTemporis(bill: BillDomain)
    fun generateAmortisationWithoutProtataTemporis(bill: BillDomain)
    fun generateDepreciationAmortisation(bill: BillDomain)
}
