package ma.worldaudit.achat.infra.model

import ma.worldaudit.achat.domain.model.TVADomain

enum class TVA(val value: Double) {
    TVA_0(0.0),
    TVA_10(10.0),
    TVA_20(20.0)
}

fun TVA.toDomain(): TVADomain {
    val tvaDomain: TVADomain
    when (this) {
        TVA.TVA_0 -> tvaDomain = TVADomain.TVA_0
        TVA.TVA_10 -> tvaDomain = TVADomain.TVA_10
        TVA.TVA_20 -> tvaDomain = TVADomain.TVA_20
    }

    return tvaDomain
}
