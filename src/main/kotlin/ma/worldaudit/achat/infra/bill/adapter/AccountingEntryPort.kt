package ma.worldaudit.achat.infra.bill.adapter

import ma.worldaudit.achat.domain.model.AccountingEntryDomain
import ma.worldaudit.achat.domain.port.infra.IAccountingEntryRepositoryPort
import ma.worldaudit.achat.domain.model.toInfra
import ma.worldaudit.achat.infra.bill.service.IAccountingEntryServices
import ma.worldaudit.achat.infra.model.toDomain
import org.springframework.stereotype.Component

@Component
class AccountingEntryPort(
    private val ecritureComptableRepository: IAccountingEntryServices,
) : IAccountingEntryRepositoryPort {
    override fun getAccountingEntry(): List<AccountingEntryDomain> {
        return ecritureComptableRepository.getAll().toDomain()
    }

    override fun addAccountingEntry(accountingEntry: AccountingEntryDomain) {
        ecritureComptableRepository.save(accountingEntry.toInfra())
    }

    override fun geAccountingEntryById(countingId: Long): AccountingEntryDomain {
        return ecritureComptableRepository.getById(countingId).toDomain();
    }

    override fun deleteAccountingEntry(countingId: Long) {
        ecritureComptableRepository.deleteById(countingId);
    }

    override fun updateAccountingEntry(id: Long, ecritureComptable: AccountingEntryDomain) {
        ecritureComptableRepository.save(ecritureComptable.toInfra())
    }

    override fun findByBillRef(billRef: String): List<AccountingEntryDomain> {
        return ecritureComptableRepository.findByRefFacture(billRef).toDomain()
    }
}
