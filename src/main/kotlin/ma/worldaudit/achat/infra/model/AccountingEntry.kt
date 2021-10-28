package ma.worldaudit.achat.infra.model

import com.fasterxml.jackson.annotation.JsonFormat
import ma.worldaudit.achat.domain.model.AccountingEntryDomain
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "Accounting_entry")
data class AccountingEntry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var dateComptabilisation: Date? = null,
    var libelle: String? = null,
    var typology: Typology? = null,
    var montantDebit: Double = 0.0,
    var montantCredit: Double = 0.0,
    var refFacture: String? = null,
    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    @JsonFormat(timezone="UTC")
    var createdDate: Date? = null,
    @Column(name = "modified_date")
    @LastModifiedDate
    @JsonFormat(timezone="UTC")
    var modifiedDate: Date? = null,
    @Column(name = "created_by")
    @CreatedBy
    var createdBy: String? = null,
    @Column(name = "modified_by")
    @LastModifiedBy
    var modifiedBy: String? = null
)

fun AccountingEntry.toDomain() = AccountingEntryDomain(
    id = id,
    modifiedBy = modifiedBy,
    libelle = libelle,
    typologie = typology!!.toDomain(),
    modifiedDate = modifiedDate,
    createdDate = createdDate,
    createdBy = createdBy,
    refFacture = refFacture,
    dateComptabilisation = dateComptabilisation,
    montantCredit = montantCredit,
    montantDebit = montantDebit
)

fun List<AccountingEntry>.toDomain() = map { it.toDomain() }
