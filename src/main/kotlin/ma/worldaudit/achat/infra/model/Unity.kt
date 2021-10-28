package ma.worldaudit.achat.infra.model

import ma.worldaudit.achat.domain.model.UnityDomain

enum class Unity {
    UNITE,
    KG
}

fun Unity.toDomain(): UnityDomain {
    val unityDomain: UnityDomain
    when (this) {
        Unity.KG -> unityDomain = UnityDomain.KG
        Unity.UNITE -> unityDomain = UnityDomain.UNITE
    }

    return unityDomain
}
