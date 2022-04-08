package uselessmnemonic.diskord.gateway.op

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Payload<E>(
    var op: Int,
    var d: E?,
    var s: Int? = null,
    var t: String? = null,
)
