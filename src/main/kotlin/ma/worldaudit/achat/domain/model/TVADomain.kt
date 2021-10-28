package ma.worldaudit.achat.domain.model

import ma.worldaudit.achat.api.model.TvaApi
import ma.worldaudit.achat.infra.model.TVA

enum class TVADomain(val value: Double) {
    TVA_0(0.0),
    TVA_10(10.0),
    TVA_20(20.0)
}

fun TVADomain.toInfra(): TVA {
    val tva: TVA
    when (this) {
        TVADomain.TVA_0 -> tva = TVA.TVA_0
        TVADomain.TVA_10 -> tva = TVA.TVA_10
        TVADomain.TVA_20 -> tva = TVA.TVA_20
    }

    return tva
}


fun TVADomain.toApi(): TvaApi {
    val tvaApi: TvaApi
    when (this) {
        TVADomain.TVA_0 -> tvaApi = TvaApi.TVA_0
        TVADomain.TVA_10 -> tvaApi = TvaApi.TVA_10
        TVADomain.TVA_20 -> tvaApi = TvaApi.TVA_20
    }

    return tvaApi
}
