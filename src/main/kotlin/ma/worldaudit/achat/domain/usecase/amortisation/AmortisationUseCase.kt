package ma.worldaudit.achat.domain.usecase.amortisation

import ma.worldaudit.achat.domain.model.*
import ma.worldaudit.achat.domain.port.api.IFixedAssetServicesPort
import ma.worldaudit.achat.domain.port.infra.IAmortisationRepositoryPort
import ma.worldaudit.achat.domain.port.infra.IFixedAssetRepositoryPort
import ma.worldaudit.achat.infra.bill.repository.IAccountingEntryRepository
import java.util.*


class AmortisationUseCase(
    private val iAccountingEntryRepository: IAccountingEntryRepository,
    private val iAmortisationRepositoryPort: IAmortisationRepositoryPort,
    private val iFixedAssetRepositoryPort: IFixedAssetRepositoryPort
) : IFixedAssetServicesPort {

    override fun generateAmortisationEntriesWithProtataTemporis(fixedAsset: FixedAssetDomain) {
        val N = fixedAsset.durationOfUse / 12
        var NMonths = fixedAsset.durationOfUse
        var depreciationRate = 100.0 / N
        var cumulativeDepreciationN1 = 0.0
        var depreciationExpense = 0.0
        var cumulativeDepreciationN = 0.0
        var netValue = fixedAsset.amountHT
        var releasedate = null
        var outputType = null
        var salePrice = null
        var amortizationDate = fixedAsset.cessionDate!!

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        cal.time = amortizationDate

        var year = cal[Calendar.YEAR]
        var month = cal[Calendar.MONTH]
        var day = cal[Calendar.DAY_OF_MONTH]

        for (i in N downTo 1) {
            val nMonths = 12 - month
            NMonths -= nMonths
            val taux: Double = nMonths / 12.0
            println(taux)
            depreciationExpense = fixedAsset.amountHT * depreciationRate * 0.01 * taux
            // changement possible !!
            cumulativeDepreciationN1 = cumulativeDepreciationN
            cumulativeDepreciationN += depreciationExpense
            netValue -= depreciationExpense

            cal[Calendar.YEAR] = year
            cal[Calendar.MONTH] = 11
            cal[Calendar.DAY_OF_MONTH] = 31

            if (i != N)
                amortizationDate = cal.time
            val amortisationDomain = AmortisationDomain(
                0,
                amortizationDate,
                fixedAsset,
                depreciationRate,
                cumulativeDepreciationN1,
                depreciationExpense, // cumul des amortissement
                cumulativeDepreciationN,
                netValue,
                releasedate,
                outputType,
                salePrice
            )
            // save to bd
            iAmortisationRepositoryPort.addAmortisation(amortisationDomain)
            year++
            month = 0
            day = 1
        }

        if (NMonths != 0) {
            val nMonths = NMonths
            val taux = nMonths.div(12.0)
            depreciationExpense = fixedAsset.amountHT * depreciationRate * 0.01 * taux
            // changement possible !!
            cumulativeDepreciationN1 = cumulativeDepreciationN
            cumulativeDepreciationN += depreciationExpense
            netValue -= depreciationExpense

            cal[Calendar.YEAR] = year
            cal[Calendar.MONTH] = 11
            cal[Calendar.DAY_OF_MONTH] = 31
            amortizationDate = cal.time

            val amortisationDomain = AmortisationDomain(
                0,
                amortizationDate,
                fixedAsset,
                depreciationRate,
                cumulativeDepreciationN1,
                depreciationExpense, // cumul des amortissement
                cumulativeDepreciationN,
                netValue,
                releasedate,
                outputType,
                salePrice
            )
            // save to bd
            iAmortisationRepositoryPort.addAmortisation(amortisationDomain)
        }
    }

    override fun generateAmortisationEntriesWithoutProtataTemporis(fixedAsset: FixedAssetDomain) {
        val N = fixedAsset.durationOfUse / 12
        var depreciationRate = 100.0 / N
        var cumulativeDepreciationN1 = 0.0
        var depreciationExpense = 0.0
        var cumulativeDepreciationN = 0.0
        var netValue = fixedAsset.amountHT
        var releasedate = null
        var outputType = null
        var salePrice = null
        var amortizationDate = fixedAsset.cessionDate!!

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        cal.time = amortizationDate

        var year = cal[Calendar.YEAR]
        var month = cal[Calendar.MONTH]
        var day = cal[Calendar.DAY_OF_MONTH]

        for (i in N downTo 1) {
            depreciationExpense = fixedAsset.amountHT * depreciationRate * 0.01
            // changement possible !!
            cumulativeDepreciationN1 = cumulativeDepreciationN
            cumulativeDepreciationN += depreciationExpense
            netValue -= depreciationExpense

            cal[Calendar.YEAR] = year
            cal[Calendar.MONTH] = 11
            cal[Calendar.DAY_OF_MONTH] = 31

            if (i != N)
                amortizationDate = cal.time
            val amortisationDomain = AmortisationDomain(
                0,
                amortizationDate,
                fixedAsset,
                depreciationRate,
                cumulativeDepreciationN1,
                depreciationExpense, // cumul des amortissement
                cumulativeDepreciationN,
                netValue,
                releasedate,
                outputType,
                salePrice
            )
            // save to bd
            iAmortisationRepositoryPort.addAmortisation(amortisationDomain)
            year++
            month = 0
            day = 1
        }
    }

    override fun generateAccountingEntry() {
        var list = iAmortisationRepositoryPort.getAllAmortisation()
        var amortissement = 0.0
        var vna = 0.0
        list.map {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
            val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            val cal1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            // reti_reve the year from the date stored in the bd !!
            cal.time = it.amortizationDate
            // year of the Amortization !!
            var year = cal[Calendar.YEAR]
            // Cureent year
            cal1.time = Date()
            // check if the year of the amortization is the current year then we make an accounting entry '_*
            if (year == cal1[Calendar.YEAR]) {
                amortissement += it.cumulativeDepreciationN!!
                vna += it.netValue!!
            }
        }
        //amortissement
        iAccountingEntryRepository.save(
            AccountingEntryDomain(
                id = 0,
                dateComptabilisation = Date(),
                libelle = "Amortissement Mat info.",
                typologie = TypologyDomain.AMORTISSEMENT_MAT_INFORMATIQUES,
                montantDebit = amortissement,
                montantCredit = 0.0,
                refFacture = "Amortisation",
                createdDate = null,
                modifiedDate = null,
                createdBy = "system",
                modifiedBy = "system"
            ).toInfra()
        )
        //Credit

        iAccountingEntryRepository.save(
            AccountingEntryDomain(
                id = 0,
                dateComptabilisation = Date(),
                libelle = "Amortissement Mat info.",
                typologie = TypologyDomain.DOTATION_AUX_AMORTISSEMENT,
                montantDebit = 0.0,
                montantCredit = amortissement,
                refFacture = "Amortisation",
                createdDate = null,
                modifiedDate = null,
                createdBy = "system",
                modifiedBy = "system"
            ).toInfra()
        )
    }

    override fun generateAccountingEntryCession() {
        var list = iAmortisationRepositoryPort.getAllAmortisation()
        var amortissement = 0.0
        var vna = 0.0
        list.map {
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
            val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            cal.time = it.amortizationDate
            var year = cal[Calendar.YEAR]
            if (year == 2021) {
                amortissement += it.cumulativeDepreciationN!!
                vna += it.netValue!!
            }
        }

        //amortissement
        iAccountingEntryRepository.save(
            AccountingEntryDomain(
                id = 0,
                dateComptabilisation = Date(),
                libelle = "Fournisseur",
                typologie = TypologyDomain.MATERIELS_INFORMATIQUES,
                montantDebit = amortissement,
                montantCredit = 0.0,
                refFacture = "Amortisation",
                createdDate = null,
                modifiedDate = null,
                createdBy = "system",
                modifiedBy = "system"
            ).toInfra()
        )
        //vna
        iAccountingEntryRepository.save(
            AccountingEntryDomain(
                id = 0,
                dateComptabilisation = Date(),
                libelle = "VNA",
                typologie = TypologyDomain.MATERIELS_INFORMATIQUES,
                montantDebit = vna,
                montantCredit = 0.0,
                refFacture = "Amortisation",
                createdDate = null,
                modifiedDate = null,
                createdBy = "system",
                modifiedBy = "system"
            ).toInfra()
        )
        //credit
        iAccountingEntryRepository.save(
            AccountingEntryDomain(
                id = 0,
                dateComptabilisation = Date(),
                libelle = "Credit",
                typologie = TypologyDomain.MATERIELS_INFORMATIQUES,
                montantDebit = 0.0,
                montantCredit = amortissement + vna,
                refFacture = "Amortisation",
                createdDate = null,
                modifiedDate = null,
                createdBy = "system",
                modifiedBy = "system"
            ).toInfra()
        )
    }

    override fun generationCessionAccountingEntriesReleaseFixedAsset(id: Long) {
        val amortisationDomain = iAmortisationRepositoryPort.getAmortisationById(id)
        val amortising = amortisationDomain.cumulativeDepreciationN!!
        val vna = amortisationDomain.netValue!!

        //amortissement
        iAccountingEntryRepository.save(
            AccountingEntryDomain(
                id = 0,
                dateComptabilisation = Date(),
                libelle = "Cession materiel ",
                typologie = TypologyDomain.AMORTISSEMENT_MAT_INFORMATIQUES,
                montantDebit = amortising,
                montantCredit = 0.0,
                refFacture = "Amortisation",
                createdDate = null,
                modifiedDate = null,
                createdBy = "system",
                modifiedBy = "system"
            ).toInfra()
        )
        //vna
        iAccountingEntryRepository.save(
            AccountingEntryDomain(
                id = 0,
                dateComptabilisation = Date(),
                libelle = "Cession materiel ",
                typologie = TypologyDomain.VNA_IMMO,
                montantDebit = vna,
                montantCredit = 0.0,
                refFacture = "Amortisation",
                createdDate = null,
                modifiedDate = null,
                createdBy = "system",
                modifiedBy = "system"
            ).toInfra()
        )
        //credit
        iAccountingEntryRepository.save(
            AccountingEntryDomain(
                id = 0,
                dateComptabilisation = Date(),
                libelle = "Cession materiel ",
                typologie = TypologyDomain.CESSION_IMMOBILISATION,
                montantDebit = 0.0,
                montantCredit = amortising + vna,
                refFacture = "Amortisation",
                createdDate = null,
                modifiedDate = null,
                createdBy = "system",
                modifiedBy = "system"
            ).toInfra()
        )
    }

    override fun setCession(id: Long, amortisation: AmortisationDomain) {
        val amortisationDb = iFixedAssetRepositoryPort.getAmortisationById(id)
        amortisationDb.releasedate = amortisation.releasedate
        amortisationDb.outputType = amortisation.outputType
        amortisationDb.salePrice = amortisation.salePrice
        val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        cal.time = amortisation.releasedate
        var month = cal[Calendar.MONTH]
        amortisationDb.depreciationExpense = amortisationDb.depreciationExpense!!*((12-month)/12.0)
        iFixedAssetRepositoryPort.save(amortisationDb)
    }

    override fun generateAmortisationWithProtataTemporis(bill: BillDomain) {
        bill.articles!!.map {
            if (it.typology!!.equals(TypologyDomain.MATERIELS_INFORMATIQUES)) {
                var amountHT = it.prixUnitaire!! * it.quantite!!
                val montantTva: Double = if (it.montantTva == 0.0 || it.montantTva == null)
                    amountHT * (it.tva?.value ?: 0.0) * 0.01
                else
                    it.montantTva!!
                var amountTTC = amountHT + montantTva
                var libelle = "Achat de " + it.libelle!!
                var fixedItem = FixedAssetDomain(
                    id = 0,
                    purchaseDate = bill.dateEcheance!!,
                    commissioningDate = bill.dateFacture!!,
                    cessionDate = bill.dateFacture!!,
                    socialReasonSupplier = bill.supplier!!.raisonSocial!!,
                    billNumber = bill.refFacture!!,
                    libelle = libelle,
                    amountTTC = amountTTC,
                    amountHT = amountHT,
                    amountTVA = montantTva as Double,
                    tva = 20,
                    durationOfUse = 60 // 5 ans = 12 mois *5 = 60 semaines
                )
                var id = iFixedAssetRepositoryPort.addFixedAsset(fixedItem)
                fixedItem.id = id
                generateAmortisationEntriesWithProtataTemporis(fixedItem)
            }
        }
    }

    override fun generateAmortisationWithoutProtataTemporis(bill: BillDomain) {
        bill.articles!!.map {
            if (it.typology!!.equals(TypologyDomain.MATERIELS_INFORMATIQUES)) {
                var amountHT = it.prixUnitaire!! * it.quantite!!
                val montantTva: Double = if (it.montantTva == 0.0 || it.montantTva == null)
                    amountHT * (it.tva?.value ?: 0.0) * 0.01
                else
                    it.montantTva!!
                var amountTTC = amountHT + montantTva
                var libelle = "Achat de " + it.libelle!!
                var fixedItem = FixedAssetDomain(
                    id = 0,
                    purchaseDate = bill.dateEcheance!!,
                    commissioningDate = bill.dateFacture!!,
                    cessionDate = bill.dateFacture!!,
                    socialReasonSupplier = bill.supplier!!.raisonSocial!!,
                    billNumber = bill.refFacture!!,
                    libelle = libelle,
                    amountTTC = amountTTC,
                    amountHT = amountHT,
                    amountTVA = montantTva as Double,
                    tva = 20,
                    durationOfUse = 60 // 5 ans = 12 mois *5 = 60 semaines
                )
                var id = iFixedAssetRepositoryPort.addFixedAsset(fixedItem)
                fixedItem.id = id
                generateAmortisationEntriesWithoutProtataTemporis(fixedItem)
            }
        }
    }

    override fun generateDepreciationAmortisation(bill: BillDomain) {
        bill.articles!!.map {
            if (it.typology!!.equals(TypologyDomain.MATERIELS_INFORMATIQUES)) {
                var amountHT = it.prixUnitaire!! * it.quantite!!
                val montantTva: Double = if (it.montantTva == 0.0 || it.montantTva == null)
                    amountHT * (it.tva?.value ?: 0.0) * 0.01
                else
                    it.montantTva!!
                var amountTTC = amountHT + montantTva
                var libelle = "Achat de " + it.libelle!!
                var fixedItem = FixedAssetDomain(
                    id = 0,
                    purchaseDate = bill.dateEcheance!!,
                    commissioningDate = bill.dateFacture!!,
                    cessionDate = bill.dateFacture!!,
                    socialReasonSupplier = bill.supplier!!.raisonSocial!!,
                    billNumber = bill.refFacture!!,
                    libelle = libelle,
                    amountTTC = amountTTC,
                    amountHT = amountHT,
                    amountTVA = montantTva as Double,
                    tva = 20,
                    durationOfUse = 60 // 5 ans = 12 mois *5 = 60 semaines
                )
                var id = iFixedAssetRepositoryPort.addFixedAsset(fixedItem)
                fixedItem.id = id
                generateDepreciationAmortisation(fixedItem)
            }
        }
    }

    private fun generateDepreciationAmortisation(fixedAsset: FixedAssetDomain) {
        val N = fixedAsset.durationOfUse / 12
        var nMonths: Int
        var decliningCoef: Double

        var depreciationRate: Double = (100.0 / N)
        if (N in 3..4)
            decliningCoef = 1.5
        else if (N in 5..6)
            decliningCoef = 2.0
        else
            decliningCoef = 3.0

        var decliningRate = depreciationRate * decliningCoef

        var cumulativeDepreciationN1 = 0.0
        var depreciationExpense = 0.0
        var cumulativeDeprecationN = 0.0
        var netValue: Double = fixedAsset.amountHT
        var amortizationDate = fixedAsset.cessionDate

        val releasedate = Date()
        val outputType = "cession"
        val salePrice = 1.0

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        cal.time = amortizationDate
        var year = cal[Calendar.YEAR]
        var month = cal[Calendar.MONTH]
        var day = cal[Calendar.DAY_OF_MONTH]

        // test example
        //var year = 2011
        //var month = Calendar.JANUARY
        //var day = 2

        for (i in 0 until N) {
            nMonths = 12 - month
            depreciationRate = 100.0 / (N - i)
            var div = (nMonths / 12.0)
            netValue -= depreciationExpense
            decliningRate = if (decliningRate > depreciationRate) decliningRate else depreciationRate
            depreciationExpense = netValue * decliningRate * 0.01 * div

            cumulativeDepreciationN1 = cumulativeDeprecationN
            cumulativeDeprecationN += depreciationExpense

            val amortisation = AmortisationDomain(
                0,
                amortizationDate,
                fixedAsset,
                depreciationRate,
                cumulativeDepreciationN1,
                depreciationExpense, // cumul des amortissement
                cumulativeDeprecationN,
                netValue,
                releasedate,
                outputType,
                salePrice
            )
            iAmortisationRepositoryPort.addAmortisation(amortisation)
            month = 0
        }
    }

    override fun generationCessionAccountingEntriesDepreciationAmortisation(id: Long) {
        val amortisationDomain = iAmortisationRepositoryPort.getAmortisationById(id)

        val amortising = amortisationDomain.depreciationExpense!!

        //amortissement
        iAccountingEntryRepository.save(
            AccountingEntryDomain(
                id = 0,
                dateComptabilisation = Date(),
                libelle = "Cession materiel ",
                typologie = TypologyDomain.AMORTISSEMENT_MAT_INFORMATIQUES,
                montantDebit = amortising,
                montantCredit = 0.0,
                refFacture = "Amortisation",
                createdDate = null,
                modifiedDate = null,
                createdBy = "system",
                modifiedBy = "system"
            ).toInfra()
        )
        //Credit
        iAccountingEntryRepository.save(
            AccountingEntryDomain(
                id = 0,
                dateComptabilisation = Date(),
                libelle = "Cession materiel ",
                typologie = TypologyDomain.DOTATION_AUX_AMORTISSEMENT,
                montantDebit = 0.0,
                montantCredit = amortising,
                refFacture = "Amortisation",
                createdDate = null,
                modifiedDate = null,
                createdBy = "system",
                modifiedBy = "system"
            ).toInfra()
        )
    }

    override fun generationCessionAccountingEntriesSale(id: Long) {
        val amortisationDomain = iAmortisationRepositoryPort.getAmortisationById(id)

        val amortising = amortisationDomain.salePrice!!

        //amortissement
        iAccountingEntryRepository.save(
            AccountingEntryDomain(
                id = 0,
                dateComptabilisation = Date(),
                libelle = "Cession materiel ",
                typologie = TypologyDomain.CESSION_IMMOBILISATION,
                montantDebit = amortising,
                montantCredit = 0.0,
                refFacture = "Amortisation",
                createdDate = null,
                modifiedDate = null,
                createdBy = "system",
                modifiedBy = "system"
            ).toInfra()
        )
        //Credit
        iAccountingEntryRepository.save(
            AccountingEntryDomain(
                id = 0,
                dateComptabilisation = Date(),
                libelle = "Cession materiel ",
                typologie = TypologyDomain.BANQUE,
                montantDebit = 0.0,
                montantCredit = amortising,
                refFacture = "Amortisation",
                createdDate = null,
                modifiedDate = null,
                createdBy = "system",
                modifiedBy = "system"
            ).toInfra()
        )
    }
}
