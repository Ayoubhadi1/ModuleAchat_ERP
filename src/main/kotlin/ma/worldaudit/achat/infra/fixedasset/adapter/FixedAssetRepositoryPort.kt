package ma.worldaudit.achat.infra.fixedasset.adapter

import ma.worldaudit.achat.domain.model.AmortisationDomain
import ma.worldaudit.achat.domain.model.FixedAssetDomain
import ma.worldaudit.achat.domain.model.toInfra
import ma.worldaudit.achat.domain.port.infra.IFixedAssetRepositoryPort
import ma.worldaudit.achat.infra.fixedasset.repository.IAmortisationRepository
import ma.worldaudit.achat.infra.fixedasset.service.IFixedAssetServices
import ma.worldaudit.achat.infra.model.toDomain
import org.springframework.stereotype.Component

@Component
class FixedAssetRepositoryPort(private val fixedAssetRepository: IFixedAssetServices, private val iAmortisationRepository: IAmortisationRepository) : IFixedAssetRepositoryPort {
    override fun getAllFixedAsset(): List<FixedAssetDomain> {
        return fixedAssetRepository.getAll().map{it.toDomain()}
    }

    override fun getFixedAssetById(fixedAssetId: Long): FixedAssetDomain {
        return fixedAssetRepository.getById(fixedAssetId).toDomain()
    }

    override fun addFixedAsset(fixedAsset: FixedAssetDomain) : Long {
        return fixedAssetRepository.save(fixedAsset.toInfra()).id!!
    }

    override fun updateFixedAsset(fixedAssetId: Long, newFixedAsset: FixedAssetDomain) {
        TODO("Not yet implemented")
    }

    override fun deleteFixedAsset(fixedAssetId: Long) {
        fixedAssetRepository.deleteFixedAsset(fixedAssetId)
    }

    override fun updateAmortisation(fixedAssetId: Long, amortisationId: Long, newAmortisation: AmortisationDomain) {
        TODO("Not yet implemented")
    }

    override fun deleteAmortisation(fixedAssetId: Long, amortisationId: Long) {
        TODO("Not yet implemented")
    }

    override fun getAmortisationById(id: Long): AmortisationDomain {
        return iAmortisationRepository.findById(id).get().toDomain()
    }

    override fun save(amortisationDb: AmortisationDomain) {
        iAmortisationRepository.save(amortisationDb.toInfra())
    }
}
