package ma.worldaudit.achat.domain.model

import ma.worldaudit.achat.api.model.SupplierApi
import ma.worldaudit.achat.infra.model.Supplier
import java.util.*

data class SupplierDomain(
        var id : Long,
        val raisonSocial: String?,
        val ice: String?,
        val idf: String?,
        val cnss: String?,
        val registreCommerce: String?,
        val telephone: String?,
        val mail: String?,
        val adresse : String?,
        var createdDate: Date?,
        var modifiedDate: Date?,
        var createdBy: String?,
        var modifiedBy: String?
)

fun SupplierDomain.toInfra() = Supplier(
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

fun SupplierDomain.toApi() = SupplierApi(
        id,
        raisonSocial,
        ice,
        idf,
        cnss,
        registreCommerce,
        adresse,
        telephone,
        mail,
        createdDate,
        modifiedDate,
        createdBy,
        modifiedBy,
)

fun List<SupplierDomain>.toApi() = map { it.toApi() }
