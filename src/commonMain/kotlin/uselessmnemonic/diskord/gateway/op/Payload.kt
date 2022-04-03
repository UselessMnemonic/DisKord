package uselessmnemonic.diskord.gateway.op

import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@Serializable
data class Payload<E>(
    @Required var op: Int,
    @Required var s: Int?,
    @Required var t: String?,
    var d: E?
)
