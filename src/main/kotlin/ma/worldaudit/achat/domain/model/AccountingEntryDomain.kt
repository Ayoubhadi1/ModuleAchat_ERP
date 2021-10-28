package ma.worldaudit.achat.domain.model

import ma.worldaudit.achat.api.model.AccountingEntryApi
import ma.worldaudit.achat.infra.model.AccountingEntry
import java.util.*

data class AccountingEntryDomain(
    var id : Long ?,
    var dateComptabilisation: Date?,
    var libelle: String?,
    val typologie: TypologyDomain,
    var montantDebit: Double = 0.0,
    var montantCredit: Double = 0.0,
    var refFacture: String?,
    var createdDate: Date?,
    var modifiedDate: Date?,
    var createdBy: String?,
    var modifiedBy: String?
)

fun AccountingEntryDomain.toInfra() = AccountingEntry(
    id = id,
    montantDebit = montantDebit,
    libelle = libelle,
    typology = typologie.toInfra(),
    montantCredit = montantCredit,
    dateComptabilisation = dateComptabilisation,
    refFacture = refFacture,
    createdBy = createdBy,
    createdDate = createdDate,
    modifiedDate = modifiedDate,
    modifiedBy = modifiedBy
)

fun AccountingEntryDomain.toApi() = AccountingEntryApi(
    id = id!!,
    libelle = libelle,
    typologie = typologie.toApi(),
    modifiedBy = modifiedBy,
    modifiedDate = modifiedDate,
    createdDate = createdDate,
    createdBy = createdBy,
    refFacture = refFacture,
    dateComptabilisation = dateComptabilisation,
    montantCredit = montantCredit,
    montantDebit = montantDebit
)

fun List<AccountingEntryDomain>.toInfra() = map { it.toInfra() }

fun List<AccountingEntryDomain>.toApi() = map { it.toApi() }

