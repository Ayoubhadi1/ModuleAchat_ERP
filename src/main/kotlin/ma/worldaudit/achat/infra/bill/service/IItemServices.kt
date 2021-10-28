package ma.worldaudit.achat.infra.bill.service

import ma.worldaudit.achat.infra.model.Item

interface IItemServices {
    fun getAll(): List<Item>
    fun save(item: Item)
    fun getById(articleId: Long): Item
    fun deleteById(articleId: Long)
    fun deleteArticle(item: Item)
}