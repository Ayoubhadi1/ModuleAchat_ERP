package ma.worldaudit.achat.infra.bill.service.impl

import ma.worldaudit.achat.infra.model.Item
import ma.worldaudit.achat.infra.bill.repository.IItemRepository
import ma.worldaudit.achat.infra.bill.service.IItemServices
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class ItemServices(private val iItemRepository: IItemRepository) : IItemServices{
    override fun getAll(): List<Item> {
        return iItemRepository.findAll()
    }

    override fun save(item: Item) {
        iItemRepository.save(item)
    }

    override fun getById(articleId: Long): Item {
        return iItemRepository.findById(articleId).get()
    }

    override fun deleteById(articleId: Long) {
        val article = getById(articleId)
        deleteArticle(article)
    }

    override fun deleteArticle(item: Item) {
        iItemRepository.deleteById(item.id)
    }
}