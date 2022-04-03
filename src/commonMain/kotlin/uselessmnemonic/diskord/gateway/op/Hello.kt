package uselessmnemonic.diskord.gateway.op

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hello(
    @SerialName("heartbeat_interval")
    val heartbeatInterval: Int
)
