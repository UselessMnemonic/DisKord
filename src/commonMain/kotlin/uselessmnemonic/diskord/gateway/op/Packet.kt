package uselessmnemonic.diskord.gateway.op

import kotlinx.serialization.Serializable

@Serializable
data class Packet<E>(
    val op: Int,
    val d: E?,
    val s: Int? = null,
    val t: String? = null,
)
