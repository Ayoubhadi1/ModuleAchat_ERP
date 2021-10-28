package ma.worldaudit.achat.infra.fixedasset.service.impl

import ma.worldaudit.achat.infra.fixedasset.repository.IAmortisationRepository
import ma.worldaudit.achat.infra.fixedasset.service.IAmortisationServices
import ma.worldaudit.achat.infra.model.Amortisation
import org.springframework.stereotype.Service

@Service
class AmortisationServices(private val amortisationRepository: IAmortisationRepository) : IAmortisationServices {
    override fun getAll(): List<Amortisation> {
        return amortisationRepository.findAll()
    }

    override fun getById(amortisationId: Long): Amortisation {
        return amortisationRepository.findById(amortisationId).get()
    }

    override fun save(amortisation: Amortisation) {
        amortisationRepository.save(amortisation)
    }

    override fun deleteById(amortisationId: Long) {
        amortisationRepository.deleteById(amortisationId)
    }
}
