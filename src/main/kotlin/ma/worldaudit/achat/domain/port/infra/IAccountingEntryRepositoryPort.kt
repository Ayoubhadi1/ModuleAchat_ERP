package ma.worldaudit.achat.domain.port.infra


import ma.worldaudit.achat.domain.model.AccountingEntryDomain

interface IAccountingEntryRepositoryPort {
    fun getAccountingEntry(): List<AccountingEntryDomain>
    fun addAccountingEntry(accountingEntry: AccountingEntryDomain)
    fun geAccountingEntryById(countingId: Long): AccountingEntryDomain
    fun deleteAccountingEntry(countingId: Long)
    fun updateAccountingEntry(id: Long, accountingEntry: AccountingEntryDomain)
    fun findByBillRef(billRef: String): List<AccountingEntryDomain>
}
