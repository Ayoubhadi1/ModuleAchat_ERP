package ma.worldaudit.achat.domain.model

import ma.worldaudit.achat.api.model.ItemApi
import ma.worldaudit.achat.infra.model.Item
import java.util.*

data class ItemDomain(
    var id : Long,
    var libelle: String?,
    var typology: TypologyDomain?,
    var quantite: Double?,
    var unity: UnityDomain?,
    var prixUnitaire: Double?,
    var montantTva: Double?,
    var tva: TVADomain?,
    var createdDate: Date?,
    var modifiedDate: Date?,
    var createdBy: String?,
    var modifiedBy: String?
)


fun ItemDomain.toInfra() = Item(
    id = id,
    libelle = libelle,
    typology = typology!!.toInfra(),
    quantite = quantite,
    unity = unity!!.toInfra(),
    prixUnitaire = prixUnitaire,
    montantTva = montantTva,
    tva = tva!!.toInfra(),
    createdDate = createdDate,
    modifiedDate = modifiedDate,
    createdBy = createdBy,
    modifiedBy = modifiedBy
)

fun ItemDomain.toApi() = ItemApi(
    id = id,
    libelle = libelle,
    typology = typology!!.toApi(),
    quantite = quantite,
    unity = unity!!.toApi(),
    prixUnitaire = prixUnitaire,
    montantTva = montantTva,
    tva = tva!!.toApi(),
    createdDate = createdDate,
    modifiedDate = modifiedDate,
    createdBy = createdBy,
    modifiedBy = modifiedBy
)

fun List<ItemDomain>.toInfra() = map { it.toInfra() }

fun List<ItemDomain>.toApi() = map { it.toApi() }
