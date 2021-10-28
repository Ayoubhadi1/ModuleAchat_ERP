package ma.worldaudit.achat.infra.bill.service

import ma.worldaudit.achat.infra.model.Bill
import org.springframework.data.domain.Page

interface IBillServices {
    fun getById(idFacture: Long): Bill
    fun save(billToInfra: Bill) : Bill
    fun deleteFacture(bill: Bill)
    fun getAll(page: Int, size: Int): Page<Bill>
    fun getAll(): List<Bill>
}
