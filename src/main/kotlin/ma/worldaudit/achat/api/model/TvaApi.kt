package ma.worldaudit.achat.api.model

import ma.worldaudit.achat.domain.model.TVADomain

enum class TvaApi(val value: Double) {
    TVA_0(0.0),
    TVA_10(10.0),
    TVA_20(20.0)
}

fun TvaApi.toDomain(): TVADomain {
    val tvaDomain: TVADomain

    when (this) {
        TvaApi.TVA_0 -> tvaDomain = TVADomain.TVA_0
        TvaApi.TVA_10 -> tvaDomain = TVADomain.TVA_10
        TvaApi.TVA_20 -> tvaDomain = TVADomain.TVA_20
    }
    return tvaDomain
}
