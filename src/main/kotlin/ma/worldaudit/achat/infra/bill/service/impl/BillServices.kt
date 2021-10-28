package ma.worldaudit.achat.infra.bill.service.impl

import ma.worldaudit.achat.infra.model.Bill
import ma.worldaudit.achat.infra.bill.repository.IBillRepository
import ma.worldaudit.achat.infra.bill.service.IBillServices
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
open class BillServices(
    private val iBillRepository: IBillRepository
) : IBillServices {
    override fun getById(idFacture: Long): Bill {
        return iBillRepository.findById(idFacture).get()
    }

    override fun save(fact: Bill): Bill {
        return iBillRepository.save(fact)
    }

    override fun deleteFacture(bill: Bill) {
        iBillRepository.delete(bill)
    }

    override fun getAll(page: Int, size: Int): Page<Bill> {
        return iBillRepository.findAll(PageRequest.of(page, size))
    }

    override fun getAll(): List<Bill> {
        return iBillRepository.findAll()
    }
}
