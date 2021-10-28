package ma.worldaudit.achat.domain.model

import ma.worldaudit.achat.api.model.TypologyApi
import ma.worldaudit.achat.infra.model.Typology

enum class TypologyDomain(val value: Int) {
    MATERIELS_INFORMATIQUES(235),
    ACHAT(613),
    TVA(3455),
    FOURNISSEUR(4411),
    AMORTISSEMENT_MAT_INFORMATIQUES(2835),
    DOTATION_AUX_AMORTISSEMENT(6193),
    VNA_IMMO(658),
    BANQUE(514),
    CESSION_IMMOBILISATION(751)
}

fun TypologyDomain.toInfra(): Typology {
    val typology: Typology
    when (this) {
        TypologyDomain.ACHAT -> typology = Typology.ACHAT
        TypologyDomain.FOURNISSEUR -> typology = Typology.FOURNISSEUR
        TypologyDomain.TVA -> typology = Typology.TVA
        TypologyDomain.MATERIELS_INFORMATIQUES -> typology = Typology.MATERIELS_INFORMATIQUES
        TypologyDomain.VNA_IMMO -> typology = Typology.VNA_IMMO
        TypologyDomain.BANQUE -> typology = Typology.BANQUE
        TypologyDomain.CESSION_IMMOBILISATION -> typology = Typology.CESSION_IMMOBILISATION
        TypologyDomain.AMORTISSEMENT_MAT_INFORMATIQUES -> typology = Typology.AMORTISSEMENT_MAT_INFORMATIQUES
        TypologyDomain.DOTATION_AUX_AMORTISSEMENT -> typology = Typology.DOTATION_AUX_AMORTISSEMENT
    }
    return typology
}

fun TypologyDomain.toApi(): TypologyApi {
    val typologyApi: TypologyApi
    when (this) {
        TypologyDomain.ACHAT -> typologyApi = TypologyApi.ACHAT
        TypologyDomain.FOURNISSEUR -> typologyApi = TypologyApi.FOURNISSEUR
        TypologyDomain.TVA -> typologyApi = TypologyApi.TVA
        TypologyDomain.MATERIELS_INFORMATIQUES -> typologyApi = TypologyApi.MATERIELS_INFORMATIQUES
        TypologyDomain.BANQUE -> typologyApi = TypologyApi.BANQUE
        TypologyDomain.VNA_IMMO -> typologyApi = TypologyApi.VNA_IMMO
        TypologyDomain.AMORTISSEMENT_MAT_INFORMATIQUES -> typologyApi = TypologyApi.AMORTISSEMENT_MAT_INFORMATIQUES
        TypologyDomain.CESSION_IMMOBILISATION -> typologyApi = TypologyApi.CESSION_IMMOBILISATION
        TypologyDomain.DOTATION_AUX_AMORTISSEMENT -> typologyApi = TypologyApi.DOTATION_AUX_AMORTISSEMENT
    }
    return typologyApi
}
