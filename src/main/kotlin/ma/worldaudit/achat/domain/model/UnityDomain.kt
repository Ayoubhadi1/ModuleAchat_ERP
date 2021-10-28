package ma.worldaudit.achat.domain.model

import ma.worldaudit.achat.api.model.UnityApi
import ma.worldaudit.achat.infra.model.Unity

enum class UnityDomain {
    UNITE,
    KG
}

fun UnityDomain.toInfra(): Unity {
    val unity: Unity
    when (this) {
        UnityDomain.KG -> unity = Unity.KG
        UnityDomain.UNITE -> unity = Unity.UNITE
    }

    return unity
}

fun UnityDomain.toApi(): UnityApi {
    val unityApi: UnityApi
    when (this) {
        UnityDomain.KG -> unityApi = UnityApi.KG
        UnityDomain.UNITE -> unityApi = UnityApi.UNITE
    }

    return unityApi
}

