package ma.worldaudit.achat.api.amortisatiom.adapter

import ma.worldaudit.achat.api.model.AmortisationApi
import ma.worldaudit.achat.api.model.toDomain
import ma.worldaudit.achat.domain.port.api.IBillServicesPort
import ma.worldaudit.achat.domain.port.api.IFixedAssetServicesPort
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class AmortisationController(
    private val iFixedAssetServicesPort: IFixedAssetServicesPort,
    private val iBillServicesPort: IBillServicesPort
) {
    @PutMapping("/bills/{id}/amortisation/WithProtata")
    fun generateLinearAmortizationWithProtat(@PathVariable id: Long) {
        iFixedAssetServicesPort.generateAmortisationWithProtataTemporis(iBillServicesPort.getBill(id))
    }

    @PutMapping("/bills/{id}/amortisation/withoutProtat")
    fun generateLinearAmortizationWithoutProtata(@PathVariable id: Long) {
        iFixedAssetServicesPort.generateAmortisationWithoutProtataTemporis(iBillServicesPort.getBill(id))
    }

    @PutMapping("/bills/{id}/amortisation/Degressif")
    fun generateDegressifAmortization(@PathVariable id: Long) {
        iFixedAssetServicesPort.generateDepreciationAmortisation(iBillServicesPort.getBill(id))
    }

    @PutMapping("/amortisations/generateAccountingEntries")
    fun generateAccountingEntries() {
        iFixedAssetServicesPort.generateAccountingEntry()
    }

    @PutMapping("/amortisations/{id}/cession/DeprecationAmortisation")
    fun generationCessionAccountingEntriesDepreciationAmortisation(@PathVariable id :Long) {
        iFixedAssetServicesPort.generationCessionAccountingEntriesDepreciationAmortisation(id)
    }

    @PutMapping("/amortisations/{id}/setCession")
    fun setCession(@PathVariable id: Long, @RequestBody amortisation: AmortisationApi) {
        iFixedAssetServicesPort.setCession(id, amortisation.toDomain())
    }

    @PutMapping("/amortisations/{id}/cession/Sale")
    fun generationCessionAccountingEntriesSale(@PathVariable id:Long) {
        iFixedAssetServicesPort.generationCessionAccountingEntriesSale(id)
    }

    @PutMapping("/amortisations/{id}/cession/Release")
    fun generationCessionAccountingEntriesReleaseFixedAsset(@PathVariable id : Long) {
        iFixedAssetServicesPort.generationCessionAccountingEntriesReleaseFixedAsset(id)
    }
}