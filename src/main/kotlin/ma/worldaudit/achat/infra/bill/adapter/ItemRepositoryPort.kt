package ma.worldaudit.achat.infra.bill.adapter

import ma.worldaudit.achat.domain.model.ItemDomain
import ma.worldaudit.achat.domain.port.infra.IItemRepositoryPort
import ma.worldaudit.achat.domain.model.toInfra
import ma.worldaudit.achat.infra.bill.service.IItemServices
import ma.worldaudit.achat.infra.model.toDomain
import org.springframework.stereotype.Component

@Component
class ItemRepositoryPort(
    private val articleservices: IItemServices,
) : IItemRepositoryPort {
    override fun getAllItems(): List<ItemDomain> {
        return articleservices.getAll().toDomain();
    }

    override fun addItem(item: ItemDomain) {
        articleservices.save(item.toInfra())
    }

    override fun addItem(articleList: List<ItemDomain>) {
        articleList.map {
            articleservices.save(it.toInfra())
        }
    }

    override fun getItemById(itemId: Long): ItemDomain {
        return articleservices.getById(itemId).toDomain()
    }

    override fun deleteItem(itemId: Long) {
        return articleservices.deleteById(itemId);
    }
}
