package ma.worldaudit.achat.infra.bill.service.impl

import ma.worldaudit.achat.infra.model.AccountingEntry
import ma.worldaudit.achat.infra.bill.repository.IAccountingEntryRepository
import ma.worldaudit.achat.infra.bill.service.IAccountingEntryServices
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class ECServices(private val iAccountingEntryRepository: IAccountingEntryRepository) :IAccountingEntryServices{
    override fun getAll(): List<AccountingEntry> {
       return  iAccountingEntryRepository.findAll()
    }

    override fun getById(ecritureId: Long): AccountingEntry {
        return iAccountingEntryRepository.findById(ecritureId).get()
    }

    override fun deleteById(ecritureId: Long) {
        val EC = getById(ecritureId)
        iAccountingEntryRepository.delete(EC)
    }

    override fun save(accountingEntry: AccountingEntry) {
        iAccountingEntryRepository.save(accountingEntry)
    }

    override fun findByRefFacture(refFacture: String): List<AccountingEntry> {
        return iAccountingEntryRepository.findByRefFacture(refFacture)
    }

    override fun delete(it: AccountingEntry) {
        iAccountingEntryRepository.delete(it)
    }
}