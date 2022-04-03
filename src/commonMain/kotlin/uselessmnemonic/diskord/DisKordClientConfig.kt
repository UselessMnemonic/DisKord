package uselessmnemonic.diskord

import uselessmnemonic.diskord.gateway.GatewayCompression

class DisKordClientConfig (
    val tokenType: TokenType,
    val token: String,
    val messageCacheSize: Int = 100,
    val reconnectAttemps: Int = 5,
    val reconnectIndefinitely: Boolean = false,
    val compression: GatewayCompression = GatewayCompression.NONE
)
