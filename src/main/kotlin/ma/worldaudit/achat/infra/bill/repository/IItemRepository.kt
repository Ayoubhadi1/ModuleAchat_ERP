package ma.worldaudit.achat.infra.bill.repository
import ma.worldaudit.achat.infra.model.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IItemRepository : JpaRepository<Item, Long> {
}
