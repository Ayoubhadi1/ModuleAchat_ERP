package ma.worldaudit.achat.api.model

import ma.worldaudit.achat.domain.model.UnityDomain

enum class UnityApi {
    UNITE,
    KG
}

fun UnityApi.toDomain(): UnityDomain {
    val unityDomain: UnityDomain
    when (this) {
        UnityApi.KG -> unityDomain = UnityDomain.KG
        UnityApi.UNITE -> unityDomain = UnityDomain.UNITE
    }

    return unityDomain
}
