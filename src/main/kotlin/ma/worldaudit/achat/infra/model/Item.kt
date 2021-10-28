package ma.worldaudit.achat.infra.model

import com.fasterxml.jackson.annotation.JsonFormat
import ma.worldaudit.achat.domain.model.ItemDomain
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "items")
data class Item(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var libelle: String? = null,
    @Enumerated(EnumType.STRING)
    var typology: Typology? = null,
    var quantite: Double? = null,
    @Enumerated(EnumType.STRING)
    var unity: Unity? = null,
    var prixUnitaire: Double? = null,
    var montantTva: Double? = null,
    var tva: TVA? = null,
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

fun Item.toDomain(): ItemDomain =
    ItemDomain(
        id = id!!,
        libelle = libelle,
        typology = typology!!.toDomain(),
        quantite = quantite,
        unity = unity!!.toDomain(),
        prixUnitaire = prixUnitaire,
        montantTva = montantTva,
        tva = tva!!.toDomain(),
        createdDate = createdDate,
        modifiedDate = modifiedDate,
        createdBy = createdBy,
        modifiedBy = modifiedBy
    )

fun List<Item>.toDomain(): List<ItemDomain> = map { it.toDomain() }
