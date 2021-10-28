package ma.worldaudit.achat.infra.fixedasset.adapter

import ma.worldaudit.achat.domain.model.AmortisationDomain
import ma.worldaudit.achat.domain.model.toInfra
import ma.worldaudit.achat.domain.port.infra.IAmortisationRepositoryPort
import ma.worldaudit.achat.infra.fixedasset.service.IAmortisationServices
import ma.worldaudit.achat.infra.model.toDomain
import org.springframework.stereotype.Component

@Component
class AmortisationRepositoryPort(private val iAmortisationServices: IAmortisationServices) : IAmortisationRepositoryPort {
    override fun getAllAmortisation(): List<AmortisationDomain> {
        return iAmortisationServices.getAll().map { it.toDomain() }
    }

    override fun getAmortisationById(amortisationId: Long): AmortisationDomain {
        return iAmortisationServices.getById(amortisationId).toDomain()
    }

    override fun addAmortisation(amortisation: AmortisationDomain) {
        iAmortisationServices.save(amortisation.toInfra())
    }

    override fun deleteAmortisation(amortisationId: Long) {
        iAmortisationServices.deleteById(amortisationId)
    }

    override fun updateAmortisation(amortisationId: Long, newAmortisation: AmortisationDomain) {
        var amortisation=newAmortisation
        amortisation.id=getAmortisationById(amortisationId).id
        iAmortisationServices.save(amortisation.toInfra())
    }
}
