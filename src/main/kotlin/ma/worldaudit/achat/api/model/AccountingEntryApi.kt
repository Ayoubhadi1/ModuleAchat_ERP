package ma.worldaudit.achat.api.model

import ma.worldaudit.achat.domain.model.AccountingEntryDomain
import java.util.*

data class AccountingEntryApi(
    var id : Long,
    var dateComptabilisation: Date?,
    var libelle: String?,
    var typologie: TypologyApi,
    var refFacture: String?,
    var montantDebit: Double = 0.0,
    var montantCredit: Double = 0.0,
    var createdDate: Date?,
    var modifiedDate: Date?,
    var createdBy: String?,
    var modifiedBy: String?
)

fun AccountingEntryApi.toDomain() = AccountingEntryDomain(
    id = id,
    libelle = libelle,
    typologie = typologie.toDomain(),
    modifiedBy = modifiedBy,
    modifiedDate = modifiedDate,
    createdDate = createdDate,
    createdBy = createdBy,
    refFacture = refFacture,
    dateComptabilisation = dateComptabilisation,
    montantCredit = montantCredit,
    montantDebit = montantDebit
)

fun List<AccountingEntryApi>.toDomain() = map { it.toDomain() }
