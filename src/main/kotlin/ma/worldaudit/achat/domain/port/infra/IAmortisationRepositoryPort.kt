package ma.worldaudit.achat.domain.port.infra

import ma.worldaudit.achat.domain.model.AmortisationDomain

interface IAmortisationRepositoryPort {
    fun getAllAmortisation(): List<AmortisationDomain>
    fun getAmortisationById(amortisationId: Long): AmortisationDomain
    fun addAmortisation(amortisation: AmortisationDomain)
    fun deleteAmortisation(amortisationId: Long)
    fun updateAmortisation(amortisationId: Long, newAmortisation: AmortisationDomain)
}
