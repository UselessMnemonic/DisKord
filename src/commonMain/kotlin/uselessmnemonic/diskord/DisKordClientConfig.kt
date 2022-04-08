package uselessmnemonic.diskord

import io.ktor.client.request.*
import io.ktor.http.*
import uselessmnemonic.diskord.gateway.ApiLevel
import uselessmnemonic.diskord.gateway.GatewayCompression
import uselessmnemonic.diskord.gateway.GatewayIntents
import uselessmnemonic.diskord.gateway.op.Identify

class DisKordClientConfig (
    val tokenType: TokenType,
    val token: String,
    val messageCacheSize: Int = 100,
    val reconnectAttempts: Int = 1,
    val reconnectIndefinitely: Boolean = false,
    val compression: GatewayCompression = GatewayCompression.NONE,
    val intents: Int
) {
    fun makeAuthHeaders(): HeadersBuilder.() -> Unit = {
        append(HttpHeaders.Accept, "application/json")
        append(HttpHeaders.UserAgent, "DiscordBot (uselessmnemonic/, v0.1-alpha); DisKord")
        append(HttpHeaders.Authorization, "${tokenType.prefix} $token")
    }

    fun makeWebsocketRequest(): HttpRequestBuilder.() -> Unit = {
        headers(makeAuthHeaders())
        parameter("v", ApiLevel.V9.code)
        parameter("encoding", "json")
    }

    fun makeIdentity() = Identify(
        token,
        intents
    )
}
