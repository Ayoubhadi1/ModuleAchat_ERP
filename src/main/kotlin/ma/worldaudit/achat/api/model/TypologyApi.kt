package ma.worldaudit.achat.api.model

import ma.worldaudit.achat.domain.model.TypologyDomain

enum class TypologyApi(val value: Int) {
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

fun TypologyApi.toDomain(): TypologyDomain {
    val typologyDomain: TypologyDomain
    when (this) {
        TypologyApi.ACHAT -> typologyDomain = TypologyDomain.ACHAT
        TypologyApi.FOURNISSEUR -> typologyDomain = TypologyDomain.FOURNISSEUR
        TypologyApi.TVA -> typologyDomain = TypologyDomain.TVA
        TypologyApi.MATERIELS_INFORMATIQUES -> typologyDomain = TypologyDomain.MATERIELS_INFORMATIQUES
        TypologyApi.BANQUE -> typologyDomain = TypologyDomain.BANQUE
        TypologyApi.VNA_IMMO -> typologyDomain = TypologyDomain.VNA_IMMO
        TypologyApi.CESSION_IMMOBILISATION -> typologyDomain = TypologyDomain.CESSION_IMMOBILISATION
        TypologyApi.AMORTISSEMENT_MAT_INFORMATIQUES -> typologyDomain = TypologyDomain.AMORTISSEMENT_MAT_INFORMATIQUES
        TypologyApi.DOTATION_AUX_AMORTISSEMENT -> typologyDomain = TypologyDomain.DOTATION_AUX_AMORTISSEMENT
    }
    return typologyDomain
}
