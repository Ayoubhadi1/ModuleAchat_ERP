package ma.worldaudit.achat.infra.model

import ma.worldaudit.achat.domain.model.TypologyDomain

enum class Typology(val value: Int) {
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

fun Typology.toDomain(): TypologyDomain {
    val typologyDomain: TypologyDomain
    when (this) {
        Typology.ACHAT -> typologyDomain = TypologyDomain.ACHAT
        Typology.FOURNISSEUR -> typologyDomain = TypologyDomain.FOURNISSEUR
        Typology.TVA -> typologyDomain = TypologyDomain.TVA
        Typology.MATERIELS_INFORMATIQUES -> typologyDomain = TypologyDomain.MATERIELS_INFORMATIQUES
        Typology.VNA_IMMO -> typologyDomain = TypologyDomain.VNA_IMMO
        Typology.BANQUE -> typologyDomain = TypologyDomain.BANQUE
        Typology.DOTATION_AUX_AMORTISSEMENT -> typologyDomain = TypologyDomain.DOTATION_AUX_AMORTISSEMENT
        Typology.AMORTISSEMENT_MAT_INFORMATIQUES -> typologyDomain = TypologyDomain.AMORTISSEMENT_MAT_INFORMATIQUES
        Typology.CESSION_IMMOBILISATION -> typologyDomain = TypologyDomain.CESSION_IMMOBILISATION
    }
    return typologyDomain
}
