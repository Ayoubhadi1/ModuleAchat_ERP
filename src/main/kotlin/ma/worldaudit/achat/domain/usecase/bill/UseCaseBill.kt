package ma.worldaudit.achat.domain.usecase.bill

import ma.worldaudit.achat.api.bill.FactureExceptions.FactureNotFoundException
import ma.worldaudit.achat.api.bill.articleExceptions.ArticleNotFoundException
import ma.worldaudit.achat.api.bill.ecritureComptableExceptions.EcritureComptableNotFoundException
import ma.worldaudit.achat.domain.model.ItemDomain
import ma.worldaudit.achat.domain.model.AccountingEntryDomain
import ma.worldaudit.achat.domain.model.BillDomain
import ma.worldaudit.achat.domain.model.TypologyDomain
import ma.worldaudit.achat.domain.port.api.IBillServicesPort
import ma.worldaudit.achat.domain.port.api.IFixedAssetServicesPort
import ma.worldaudit.achat.domain.port.infra.IAccountingEntryRepositoryPort
import ma.worldaudit.achat.domain.port.infra.IBillRepositoryPort
import ma.worldaudit.achat.domain.usecase.amortisation.AmortisationUseCase
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


class UseCaseBill(
    private val factureRepository: IBillRepositoryPort,
    private val ecritureComptableRepository: IAccountingEntryRepositoryPort
) : IBillServicesPort {
    fun facturePresente(idFacture : Long):Boolean{
        factureRepository.getAll().map {
            if(it.id == idFacture) return true
        }
        return false
    }
    fun articlePresente(idArticle : Long):Boolean{
        factureRepository.getAllItems().map {
            if(it.id == idArticle) return true
        }
        return false
    }
    fun ecritureComptablePresente(idEc : Long):Boolean{
        ecritureComptableRepository.getAccountingEntry().map {
            if(it.id == idEc) return true
        }
        return false
    }

    override fun getBill(idFacture: Long): BillDomain {
        if(!facturePresente(idFacture)) throw FactureNotFoundException()
        return factureRepository.getBill(idFacture)
    }

    override fun getAccountingEntry(idEC: Long): AccountingEntryDomain {
        if(!ecritureComptablePresente(idEC)) throw EcritureComptableNotFoundException()
        return ecritureComptableRepository.geAccountingEntryById(idEC)
    }

    override fun getItem(idArticle: Long): ItemDomain {
        if(!articlePresente(idArticle)) throw ArticleNotFoundException()
        return factureRepository.getItem(idArticle)
    }

    override fun getAllBills(page: Int, size: Int): List<BillDomain> {
        return factureRepository.getAll(page, size)
    }

    fun dateComptabilisation(date: Date): Date {
        var dateCompta: Date = Date()
        val currentYear: String = Calendar.getInstance().get(Calendar.YEAR).toString()
        val startDate = LocalDate.parse("$currentYear-01-01")
        val endDate = LocalDate.parse("$currentYear-12-31")

        // convert facture date to LocalDate then compare
        if (date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(startDate) &&
            date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(endDate)
        ) {

            dateCompta = date
        }
        return dateCompta
    }

    fun generateEcritureComptableAchat(articles: List<ItemDomain>, dateCompta: Date, refFacture: String) {
        articles!!.forEach {
            val libelle: String = "Achat de ${it.libelle}"
            val debit: Double = it.prixUnitaire!! * it.quantite!!
            val ecritureComptable: AccountingEntryDomain =
                AccountingEntryDomain(
                    0,
                    dateCompta,
                    libelle,
                    it.typology!!,
                    debit,
                    0.0,
                    refFacture,
                    null,
                    null,
                    "System",
                    "None"
                )
            ecritureComptableRepository.addAccountingEntry(ecritureComptable)
        }
    }

    fun generateEcritureComptableTva(articles: List<ItemDomain>, dateCompta: Date, refFacture: String) {
        articles!!.forEach {
            val libelleTVA = "TVA récupérable ${it.libelle}"
            val debit: Double = it.prixUnitaire!! * it.quantite!!
            val montantTva: Double = if (it.montantTva == 0.0 || it.montantTva == null)
                debit * (it.tva?.value ?: 0.0) * 0.01
            else
                it.montantTva!!
            val ecritureComptableTva: AccountingEntryDomain = AccountingEntryDomain(
                0,
                dateCompta,
                libelleTVA,
                TypologyDomain.ACHAT,
                montantTva,
                0.0,
                refFacture,
                null,
                null,
                "System",
                "None"
            )
            ecritureComptableRepository.addAccountingEntry(ecritureComptableTva)
        }

    }

    fun calulateTotalCredit(facture: BillDomain): Double {
        var totalCredit = 0.0
        facture.articles!!.forEach {
            val debit: Double = it.prixUnitaire!! * it.quantite!!
            val montantTva: Double = if (it.montantTva == 0.0 || it.montantTva == null)
                debit * (it.tva?.value ?: 0.0) * 0.01
            else
                it.montantTva!!
            totalCredit += debit + montantTva
        }
        return totalCredit
    }

    fun generateEcritureComptableCredit(refFacture: String, dateCompta: Date, tauxTotal: Double) {
        var libelleBill = "Bill Num ${refFacture}"
        var ecritureComptableCredit: AccountingEntryDomain = AccountingEntryDomain(
            0,
            dateCompta,
            libelleBill,
            TypologyDomain.FOURNISSEUR,
            0.0,
            tauxTotal,
            refFacture,
            null,
            null,
            "System",
            "None"
        )
        ecritureComptableRepository.addAccountingEntry(ecritureComptableCredit)
    }

    fun deleteArticlesAfterUpdate(oldBillDomain: BillDomain, updatedBillDomain: BillDomain) {

        oldBillDomain.articles!!.map { article ->
            var exist = false
            updatedBillDomain.articles!!.map {
                if (article.id!!.equals(it.id)) {
                    exist = true
                }
            }
            if (!exist) {
                factureRepository.deleteArticleById(article.id)
            }
        }
    }

    override fun addBill(facture: BillDomain) {

        var dateCompta: Date = dateComptabilisation(facture.dateFacture!!)
        var tauxTotal: Double = 0.0
        generateEcritureComptableAchat(facture.articles!!, dateCompta, facture.refFacture!!)
        generateEcritureComptableTva(facture.articles!!, dateCompta, facture.refFacture!!)
        tauxTotal = calulateTotalCredit(facture)
        generateEcritureComptableCredit(facture.refFacture!!, dateCompta, tauxTotal)
        factureRepository.addBill(facture)
    }

    fun deleteEcrituresComptable(refFacture: String) {
        val ecS: List<AccountingEntryDomain> =
            findByBillRef(refFacture).toMutableList()
        ecS.map {
            factureRepository.deleteAccountingEntry(it.id!!)
        }
    }

    fun updateFacture(facture: BillDomain) {
        deleteEcrituresComptable(facture.refFacture!!)
        addBill(facture)
    }

    override fun updateBill(id: Long, facture: BillDomain) {
        if(!facturePresente(id)) throw FactureNotFoundException()
        val oldFacture = factureRepository.getBill(id)
        deleteArticlesAfterUpdate(oldFacture, facture)
        facture.id = oldFacture.id
        updateFacture(facture)
    }

    fun getUpdatedFacture(idFacture: Long, idArticle: Long): BillDomain {
        if(!facturePresente(idFacture)) throw FactureNotFoundException()
        var facture: BillDomain = getBill(idFacture)
        val list: MutableList<ItemDomain> = mutableListOf()

        facture.articles!!.map {
            if (!(it.id)!!.equals(idArticle)) {
                list.add(it)
            }
        }
        facture.articles = list
        return BillDomain(
            id = idFacture,
            refFacture = facture.refFacture,
            supplier = facture.supplier,
            articles = list.toList(),
            dateFacture = facture.dateFacture,
            refPaiement = facture.refPaiement,
            dateEcheance = facture.dateEcheance,
            createdDate = facture.createdDate,
            modifiedDate = facture.modifiedDate,
            createdBy = facture.createdBy,
            modifiedBy = facture.modifiedBy
        )

    }

    override fun deleteItem(facture: Long, idArticle: Long) {
        if(!facturePresente(facture)) throw FactureNotFoundException()
        if(!articlePresente(idArticle)) throw ArticleNotFoundException()
        var updatedFacture = getUpdatedFacture(facture, idArticle)
        factureRepository.deleteArticleById(idArticle)
        updateFacture(updatedFacture)
    }

    override fun getAllItems(): List<ItemDomain> {
        return factureRepository.getAllItems()
    }

    override fun getAllAccountingEntries(): List<AccountingEntryDomain> {
        return ecritureComptableRepository.getAccountingEntry()
    }

    override fun updateAccountingEntry(id: Long, accountingEntryDomain: AccountingEntryDomain) {
        if(!ecritureComptablePresente(id)) throw EcritureComptableNotFoundException()
        ecritureComptableRepository.updateAccountingEntry(id, accountingEntryDomain)
    }

    override fun findByBillRef(refFacture: String): List<AccountingEntryDomain> {
        return ecritureComptableRepository.findByBillRef(refFacture)
    }

    override fun deleteBill(id: Long) {
        if(!facturePresente(id)) throw FactureNotFoundException()
        var facture = getBill(id)
        deleteEcrituresComptable(facture.refFacture!!)
        factureRepository.deleteBill(id)
    }
}
