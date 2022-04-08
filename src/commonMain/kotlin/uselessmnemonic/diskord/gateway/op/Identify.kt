package uselessmnemonic.diskord.gateway.op

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import uselessmnemonic.diskord.Platform

@Serializable
data class Identify(
    val token: String,
    val intents: Int,
    val shard: IntArray? = null,
    val compress: Boolean = false,
    @SerialName("large_threshold")
    val largeThreshold: Int = 50,
    val presence: UpdatePresence? = null,
    val properties: ConnectionProperties = ConnectionProperties(
        Platform.OS,
        "DisKord",
        "DisKord"
    )
) {
    @Serializable
    data class ConnectionProperties(
        val os: String,
        val browser: String,
        val device: String
    )

    constructor(
        token: String,
        intents: Int,
        shardId: Int,
        numShards: Int,
        compress: Boolean = false,
        largeThreshold: Int = 50,
        presence: UpdatePresence? = null,
        properties: ConnectionProperties = ConnectionProperties(
            Platform.OS,
            "DisKord",
            "DisKord"
        )) : this(
        token,
        intents,
        intArrayOf(shardId, numShards),
        compress,
        largeThreshold,
        presence,
        properties
    )
}
