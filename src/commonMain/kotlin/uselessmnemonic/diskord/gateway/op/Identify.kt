package uselessmnemonic.diskord.gateway.op

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import uselessmnemonic.diskord.Platform

@Serializable
data class Identify(
    val token: String,
    val intents: Int,
    val compress: Boolean,
    val shard: IntArray? = null,
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
        compress,
        intArrayOf(shardId, numShards),
        largeThreshold,
        presence,
        properties
    )

    override fun toString(): String {
        return "Identify(intents=$intents, compress=$compress, shard=$shard, largeThreshold=$largeThreshold, presence=$presence, properties=$properties)"
    }
}
