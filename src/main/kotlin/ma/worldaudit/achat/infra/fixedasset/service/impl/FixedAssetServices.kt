package ma.worldaudit.achat.infra.fixedasset.service.impl

import ma.worldaudit.achat.infra.fixedasset.repository.IFixedAssetRepository
import ma.worldaudit.achat.infra.fixedasset.service.IFixedAssetServices
import ma.worldaudit.achat.infra.model.FixedAsset
import org.springframework.stereotype.Service

@Service
class FixedAssetServices(private val fixedAssetRepository: IFixedAssetRepository) : IFixedAssetServices {
    override fun getById(fixedAssetId: Long): FixedAsset {
        return fixedAssetRepository.findById(fixedAssetId).get()
    }

    override fun save(fixedAsset: FixedAsset): FixedAsset {
        return fixedAssetRepository.save(fixedAsset)
    }

    override fun deleteFixedAsset(fixedAssetId: Long) {
        fixedAssetRepository.deleteById(fixedAssetId)
    }

    override fun getAll(): List<FixedAsset> {
        return fixedAssetRepository.findAll()
    }
}
