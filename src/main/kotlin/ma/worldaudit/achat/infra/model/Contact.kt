package ma.worldaudit.achat.infra.model

import com.fasterxml.jackson.annotation.JsonFormat
import ma.worldaudit.achat.domain.model.ContactDomain
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name="contacts")
data class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val nom: String? = null,
    val telephone: String? = null,
    val email: String? = null,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    var supplier: Supplier? = null,
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

fun Contact.toDomain() = ContactDomain(
    id = id!!,
    nom = nom,
    telephone = telephone,
    email = email,
    createdDate = createdDate,
    modifiedDate = modifiedDate,
    createdBy = createdBy,
    modifiedBy = modifiedBy,
    supplier = supplier!!.toDomain()
)

fun List<Contact>.toDomain() = map {
    it.toDomain()
}
