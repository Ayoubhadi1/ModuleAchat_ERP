package ma.worldaudit.achat.api.model


import ma.worldaudit.achat.domain.model.ItemDomain
import java.util.*

data class ItemApi(
    var id : Long,
    var libelle: String?,
    var typology: TypologyApi?,
    var quantite: Double?,
    var unity: UnityApi?,
    var prixUnitaire: Double?,
    var montantTva: Double?,
    var tva: TvaApi?,
    var createdDate: Date?,
    var modifiedDate: Date?,
    var createdBy: String?,
    var modifiedBy: String?
)

fun ItemApi.toDomain() = ItemDomain(
    id = id,
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

fun List<ItemApi>.toDomain() = map { it.toDomain() }
