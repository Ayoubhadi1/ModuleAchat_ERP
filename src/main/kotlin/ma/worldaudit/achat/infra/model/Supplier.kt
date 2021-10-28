package ma.worldaudit.achat.infra.model

import com.fasterxml.jackson.annotation.JsonFormat
import ma.worldaudit.achat.domain.model.SupplierDomain
import javax.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name="suppliers")
data class Supplier(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var raisonSocial: String? = null,
    var ice: String? = null,
    var idf: String? = null,
    var cnss: String? = null,
    var registreCommerce: String? = null,
    var adresse: String? = null,
    var telephone: String? = null,
    var mail: String? = null,
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

fun Supplier.toDomain() =
    SupplierDomain(
        id = id!!,
        raisonSocial = raisonSocial,
        ice = ice,
        idf = idf,
        cnss = cnss,
        registreCommerce = registreCommerce,
        adresse = adresse,
        telephone = telephone,
        mail = mail,
        createdDate = createdDate,
        modifiedDate = modifiedDate,
        createdBy = createdBy,
        modifiedBy = modifiedBy,
    )

fun List<Supplier>.toDomain() = map { it.toDomain() }
