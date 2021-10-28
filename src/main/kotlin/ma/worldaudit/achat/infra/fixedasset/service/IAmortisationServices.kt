package ma.worldaudit.achat.infra.fixedasset.service

import ma.worldaudit.achat.infra.model.Amortisation

interface IAmortisationServices {
    fun getAll(): List<Amortisation>
    fun getById(amortisationId: Long): Amortisation
    fun save(amortisation: Amortisation)
    fun deleteById(amortisationId: Long)
}
