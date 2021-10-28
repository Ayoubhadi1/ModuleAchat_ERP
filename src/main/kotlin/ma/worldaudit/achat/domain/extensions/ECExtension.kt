package ma.worldaudit.achat.domain.extensions
/*
import ma.worldaudit.achat.domain.model.AccountingEntryDomain
import ma.worldaudit.achat.domain.model.BillDomain
import ma.worldaudit.achat.domain.model.TypologyDomain
import ma.worldaudit.achat.domain.port.infra.IAccountingEntryRepositoryPort
import ma.worldaudit.achat.domain.port.infra.IBillRepositoryPort
import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import kotlin.collections.ArrayList

fun BillDomain.creerECs(ecritureComptableRepository: IAccountingEntryRepositoryPort,factureRepository : IBillRepositoryPort) {
    var dateCompta: Date = Date()

    val currentYear: String = Calendar.getInstance().get(Calendar.YEAR).toString()
    val startDate = LocalDate.parse("$currentYear-01-01")
    val endDate = LocalDate.parse("$currentYear-12-31")
    val listEC: MutableList<AccountingEntryDomain> = ArrayList()

    // convert facture date to LocalDate then compare
    if (this.dateFacture!!.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(startDate) &&
        this.dateFacture!!.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isBefore(endDate)) {

        dateCompta = this.dateFacture!!
    }

    var tauxTotal : Double = 0.0
    var libelleFacture = "Bill Num $refFacture"
    var items = this.items
    println(items.toString())
    items!!.forEach {
        val libelle: String = "Achat de ${it.libelle}"
        val libelleTVA = "TVA récupérable ${it.libelle}"
        val debit: Double = it.prixUnitaire!! * it.quantite!!
        val montantTva: Double = debit * (it.tva?.value ?: 0.0) * 0.01
        tauxTotal += debit
        tauxTotal += montantTva

        val ecritureComptable: AccountingEntryDomain = AccountingEntryDomain(dateCompta, libelle, it.typology!!, debit, 0.0,  this, null, null, null,null)
        val ecritureComptableTva: AccountingEntryDomain = AccountingEntryDomain(dateCompta, libelleTVA, TypologyDomain.ACHAT, montantTva, 0.0, this, null, null,  null,null)
        ecritureComptableRepository.addEcritureComptable(ecritureComptable)
        ecritureComptableRepository.addEcritureComptable(ecritureComptableTva)
    }

    var ecritureComptableCredit: AccountingEntryDomain = AccountingEntryDomain(dateCompta, libelleFacture, TypologyDomain.FOURNISSEUR, 0.0, tauxTotal, this, null, null, null, null)
    ecritureComptableRepository.addEcritureComptable(ecritureComptableCredit)
}
*/