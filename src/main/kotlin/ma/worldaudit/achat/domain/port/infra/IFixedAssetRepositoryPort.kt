package ma.worldaudit.achat.domain.port.infra

import ma.worldaudit.achat.domain.model.AmortisationDomain
import ma.worldaudit.achat.domain.model.FixedAssetDomain

interface IFixedAssetRepositoryPort {
    fun getAllFixedAsset(): List<FixedAssetDomain>
    fun getFixedAssetById(fixedAssetId: Long): FixedAssetDomain
    fun addFixedAsset(fixedAsset: FixedAssetDomain) : Long
    fun updateFixedAsset(fixedAssetId: Long, newFixedAsset: FixedAssetDomain)
    fun deleteFixedAsset(fixedAssetId: Long)

    fun updateAmortisation(fixedAssetId: Long, amortisationId: Long, newAmortisation: AmortisationDomain)
    fun deleteAmortisation(fixedAssetId: Long, amortisationId: Long)
    fun getAmortisationById(id: Long): AmortisationDomain
    fun save(amortisationDb: AmortisationDomain)
}
