package ma.worldaudit.achat.api.model

import ma.worldaudit.achat.domain.model.SupplierDomain
import java.util.*

data class SupplierApi(
        var id : Long,
        val raisonSocial: String? = null,
        val ice: String? = null,
        val idf: String? = null,
        val cnss: String? = null,
        val registreCommerce: String? = null,
        val adresse: String? = null,
        val telephone : String? = null,
        val mail : String? = null,
        var createdDate: Date?,
        var modifiedDate: Date?,
        var createdBy: String?,
        var modifiedBy: String?
)

fun SupplierApi.toDomain() = SupplierDomain(
        id = id,
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
