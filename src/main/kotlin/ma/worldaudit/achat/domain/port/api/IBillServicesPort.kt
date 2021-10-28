package ma.worldaudit.achat.domain.port.api

import ma.worldaudit.achat.domain.model.ItemDomain
import ma.worldaudit.achat.domain.model.AccountingEntryDomain
import ma.worldaudit.achat.domain.model.BillDomain

interface IBillServicesPort {
    fun getBill(idFacture: Long): BillDomain
    fun getAccountingEntry(idEC: Long): AccountingEntryDomain
    fun getItem(idArticle: Long): ItemDomain
    fun getAllBills(page: Int, size: Int): List<BillDomain>
    fun addBill(facture: BillDomain)
    fun updateBill(id: Long, facture: BillDomain)
    fun deleteBill(id: Long)
    fun deleteItem(facture: Long, article: Long)
    fun getAllItems(): List<ItemDomain>
    fun getAllAccountingEntries(): List<AccountingEntryDomain>
    fun updateAccountingEntry(id: Long, accountingEntryDomain: AccountingEntryDomain)
    fun findByBillRef(refFacture: String): List<AccountingEntryDomain>
}
