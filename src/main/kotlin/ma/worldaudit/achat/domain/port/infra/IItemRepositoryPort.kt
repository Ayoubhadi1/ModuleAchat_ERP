package ma.worldaudit.achat.domain.port.infra

import ma.worldaudit.achat.domain.model.ItemDomain

interface IItemRepositoryPort {
    fun getAllItems(): List<ItemDomain>
    fun addItem(item: ItemDomain)
    fun addItem(itemsList : List<ItemDomain>)
    fun getItemById(itemId: Long): ItemDomain
    fun deleteItem(itemId: Long)
}