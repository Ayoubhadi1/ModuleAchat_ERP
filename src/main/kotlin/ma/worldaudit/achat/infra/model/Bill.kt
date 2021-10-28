package ma.worldaudit.achat.infra.model

import com.fasterxml.jackson.annotation.JsonFormat
import ma.worldaudit.achat.domain.model.BillDomain
import javax.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "bills")
class Bill(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var refFacture: String? = null,
    @OneToOne(cascade = [CascadeType.ALL])
    var supplier: Supplier? = null,
    @OneToMany(cascade = [CascadeType.ALL],orphanRemoval = true)
    var items: List<Item>? = null,
    var dateFacture: Date? = null,
    var refPaiement: String? = null,
    var dateEcheance: Date? = null,
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

fun Bill.toDomain() =
    BillDomain(
        id = id!!,
        articles = items!!.toDomain(),
        dateFacture = dateFacture,
        refFacture = refFacture,
        createdBy = createdBy,
        createdDate = createdDate,
        dateEcheance = dateEcheance,
        supplier = supplier!!.toDomain(),
        modifiedBy = modifiedBy,
        modifiedDate = modifiedDate,
        refPaiement = refPaiement
    )

fun List<Bill>.toDomain() = map { it.toDomain() }
