package uselessmnemonic.diskord

import io.ktor.client.request.*
import io.ktor.http.*
import uselessmnemonic.diskord.gateway.ApiLevel
import uselessmnemonic.diskord.gateway.GatewayCompression
import uselessmnemonic.diskord.gateway.GatewayEncoding
import uselessmnemonic.diskord.gateway.op.Identify

data class DisKordClientConfig (
    val tokenType: TokenType,
    val token: String,
    val intents: Int,
    val messageCacheSize: Int = 100,
    val reconnectAttempts: Int = 1,
    val reconnectIndefinitely: Boolean = false,
    val compression: GatewayCompression = GatewayCompression.NONE,
    val encoding: GatewayEncoding = GatewayEncoding.JSON
) {
    fun makeAuthHeaders(): HeadersBuilder.() -> Unit = {
        append(HttpHeaders.Accept, "application/json")
        append(HttpHeaders.UserAgent, "DiscordBot (uselessmnemonic/, v0.1-alpha); DisKord")
        append(HttpHeaders.Authorization, "${tokenType.prefix} $token")
    }

    fun makeWebsocketRequest(ep: String): HttpRequestBuilder.() -> Unit = {
        headers(makeAuthHeaders())
        parameter("v", ApiLevel.V9.code)
        parameter("encoding", encoding.code)
        if (compression == GatewayCompression.TRANSPORT) {
            header("compress", "zlib-stream")
        }
        url(ep)
    }

    fun makeIdentity() = Identify(
        token,
        intents,
        (compression == GatewayCompression.PAYLOAD)
    )
}
