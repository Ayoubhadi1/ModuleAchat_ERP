package ma.worldaudit.achat.domain.port.infra


import ma.worldaudit.achat.domain.model.ItemDomain
import ma.worldaudit.achat.domain.model.AccountingEntryDomain
import ma.worldaudit.achat.domain.model.BillDomain

interface IBillRepositoryPort {
    fun getBill(idFacture: Long): BillDomain
    fun getAccountingEntry(idEC: Long): AccountingEntryDomain
    fun addAccountingEntries(accountingEntry: List<AccountingEntryDomain>)
    fun addAccountingEntry(accountingEntry: AccountingEntryDomain)
    fun getItem(idArticle: Long): ItemDomain
    fun getAll(page: Int, size: Int): List<BillDomain>
    fun getAll(): List<BillDomain>
    fun addBill(facture: BillDomain)
    fun deleteArticleById(idArticle: Long)
    fun deleteBill(id: Long)
    fun getAccountingEntries(): List<AccountingEntryDomain>
    fun getAccountingEntryById(accountingEntryId: Long): AccountingEntryDomain
    fun deleteAccountingEntry(accountingEntryId: Long)
    fun getAllItems(): List<ItemDomain>
}
