package ma.worldaudit.achat.infra.bill.adapter

import ma.worldaudit.achat.domain.model.ItemDomain
import ma.worldaudit.achat.domain.model.AccountingEntryDomain
import ma.worldaudit.achat.domain.model.BillDomain
import ma.worldaudit.achat.domain.port.infra.IBillRepositoryPort
import ma.worldaudit.achat.domain.model.toInfra
import ma.worldaudit.achat.infra.bill.service.IItemServices
import ma.worldaudit.achat.infra.bill.service.IAccountingEntryServices
import ma.worldaudit.achat.infra.bill.service.IBillServices
import ma.worldaudit.achat.infra.model.toDomain
import org.springframework.stereotype.Component


@Component
class BillRepositoryPort(
    private var factureServices: IBillServices,
    private var articleServices: IItemServices,
    private val ecritureComptableServices: IAccountingEntryServices,
) : IBillRepositoryPort {
    override fun getBill(idFacture: Long): BillDomain {
        return factureServices.getById(idFacture).toDomain()
    }

    override fun getAccountingEntry(idEC: Long): AccountingEntryDomain {
        return ecritureComptableServices.getById(idEC).toDomain()
    }

    override fun addAccountingEntries(accountingEntry: List<AccountingEntryDomain>) {
        accountingEntry.map {
            addAccountingEntry(it)
        }
    }

    override fun getAccountingEntries(): List<AccountingEntryDomain> {
        return ecritureComptableServices.getAll().toDomain()
    }

    override fun addAccountingEntry(accountingEntry: AccountingEntryDomain) {
        ecritureComptableServices.save(accountingEntry.toInfra())
    }

    override fun getAccountingEntryById(accountingEntryId: Long): AccountingEntryDomain {
        return ecritureComptableServices.getById(accountingEntryId).toDomain()
    }

    override fun deleteAccountingEntry(accountingEntryId: Long) {
        ecritureComptableServices.deleteById(accountingEntryId);
    }

    override fun getAllItems(): List<ItemDomain> {
        return articleServices.getAll().toDomain()
    }

    override fun getItem(articleId: Long): ItemDomain {
        return articleServices.getById(articleId).toDomain()
    }

    override fun getAll(page: Int, size: Int): List<BillDomain> {
        return factureServices.getAll(page, size).toList().toDomain()
    }

    override fun getAll(): List<BillDomain> {
        return factureServices.getAll().toList().toDomain()
    }

    override fun addBill(facture: BillDomain) {
        factureServices.save(facture.toInfra())
    }

    override fun deleteArticleById(idArticle: Long) {
        articleServices.deleteById(idArticle)
    }

    override fun deleteBill(id: Long) {
        val facture = factureServices.getById(id)
        factureServices.deleteFacture(facture)
    }
}
