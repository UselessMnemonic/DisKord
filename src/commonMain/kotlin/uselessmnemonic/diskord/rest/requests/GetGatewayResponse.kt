package uselessmnemonic.diskord.rest.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetGatewayResponse(
    var url: String,
    var shards: Int,
    @SerialName("session_start_limit")
    var sessionStartLimit: SessionStartLimit
)

@Serializable
data class SessionStartLimit(
    var total: Int,
    var remaining: Int,
    @SerialName("reset_after")
    var resetAfter: Int,
    @SerialName("max_concurrency")
    var maxConcurrency: Int
)
