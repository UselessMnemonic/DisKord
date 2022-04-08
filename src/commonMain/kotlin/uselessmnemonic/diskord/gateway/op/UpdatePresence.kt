package uselessmnemonic.diskord.gateway.op

import kotlinx.serialization.Serializable

@Serializable
data class UpdatePresence(
    val status: String
)
