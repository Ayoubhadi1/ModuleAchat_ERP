package ma.worldaudit.achat.infra.fixedasset.repository

import ma.worldaudit.achat.infra.model.FixedAsset
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IFixedAssetRepository : JpaRepository<FixedAsset, Long> {
}
