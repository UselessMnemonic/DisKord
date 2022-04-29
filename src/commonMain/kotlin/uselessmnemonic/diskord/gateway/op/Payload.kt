package uselessmnemonic.diskord.gateway.op

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Payload<E>(
    val op: Int,
    val d: E?,
    val s: Int? = null,
    val t: String? = null,
)
