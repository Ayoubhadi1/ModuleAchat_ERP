package ma.worldaudit.achat.api.bill.adapter

import ma.worldaudit.achat.api.bill.FactureExceptions.FactureNotFoundException
import ma.worldaudit.achat.api.model.ItemApi
import ma.worldaudit.achat.api.model.AccountingEntryApi
import ma.worldaudit.achat.api.model.BillApi
import ma.worldaudit.achat.api.model.toDomain
import ma.worldaudit.achat.domain.model.toApi
import ma.worldaudit.achat.domain.port.api.IBillServicesPort
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
class BillController(
    private val iBillService: IBillServicesPort
) {
    @PostMapping("/bills/")
    fun addBill(@RequestBody facture: BillApi) {
        iBillService.addBill(facture.toDomain())
    }

    @GetMapping("/bills/{id}/")
    fun getBill(@PathVariable id: Long): BillApi {
        try {
            return iBillService.getBill(id).toApi()
        } catch (e: FactureNotFoundException) {
            throw FactureNotFoundException()
        }
    }

    @GetMapping("/bills/")
    fun getAllBills(
        @RequestParam(name = "page", defaultValue = "0") page: Int,
        @RequestParam(name = "size", defaultValue = "10") size: Int
    ): List<BillApi> {
        return iBillService.getAllBills(page, size).toApi()
    }

    @PutMapping("/bills/{id}")
    fun updateBill(@PathVariable id: Long, @RequestBody facture: BillApi) { // include adding new items !!!
        try {
            iBillService.updateBill(id, facture.toDomain())
        } catch (e: FactureNotFoundException) {
            throw FactureNotFoundException()
        }
    }

    @DeleteMapping("/bills/{billId}/items/{itemId}")
    fun deleteItem(@PathVariable billId: Long, @PathVariable itemId: Long) {
        iBillService.deleteItem(billId, itemId)
        updateBill(billId, iBillService.getBill(billId).toApi())
    }

    @DeleteMapping("/bills/{id}")
    fun deleteBill(@PathVariable id: Long) {
        try {
            iBillService.deleteBill(id)
        } catch (e: FactureNotFoundException) {
            throw FactureNotFoundException()
        }
    }

    @GetMapping("/bills/{billId}/accountingEntries/{idEC}")
    fun getAccountingEntry(@PathVariable billId: Long, @PathVariable idEC: Long): AccountingEntryApi {
        return iBillService.getAccountingEntry(idEC).toApi()
    }

    @GetMapping("/bills/all/items/")
    fun getAllItems(): List<ItemApi> {
        return iBillService.getAllItems().map {
            it.toApi()
        }
    }

    @GetMapping("/accountingEntries/")
    fun getAccountingEntries(): List<AccountingEntryApi> {
        return iBillService.getAllAccountingEntries().map { it.toApi() }
    }

    @PutMapping("/accountingEntries/{id}/")
    fun updateAccountingEntries(@PathVariable id: Long, @RequestBody ecuritureComptableApi: AccountingEntryApi) {
        iBillService.updateAccountingEntry(id, ecuritureComptableApi.toDomain())
    }
}
