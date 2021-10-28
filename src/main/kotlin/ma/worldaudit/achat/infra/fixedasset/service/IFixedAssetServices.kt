package ma.worldaudit.achat.infra.fixedasset.service

import ma.worldaudit.achat.infra.model.FixedAsset

interface IFixedAssetServices {
    fun getById(fixedAssetId: Long): FixedAsset
    fun save(fixedAsset: FixedAsset): FixedAsset
    fun deleteFixedAsset(fixedAssetId: Long)
    fun getAll(): List<FixedAsset>
}
