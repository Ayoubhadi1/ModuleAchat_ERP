package ma.worldaudit.achat.infra.bill.service

import ma.worldaudit.achat.infra.model.AccountingEntry

interface IAccountingEntryServices {
    fun getAll(): List<AccountingEntry>
    fun getById(ecritureId: Long): AccountingEntry
    fun deleteById(ecritureId: Long)
    fun save(accountingEntry: AccountingEntry)
    fun findByRefFacture(refFacture: String): List<AccountingEntry>
    fun delete(it: AccountingEntry)
}