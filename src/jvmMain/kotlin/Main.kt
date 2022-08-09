import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.websocket.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onSubscription
import kotlinx.coroutines.flow.subscribe
import uselessmnemonic.diskord.DisKordClient
import uselessmnemonic.diskord.DisKordClientConfig
import uselessmnemonic.diskord.TokenType
import uselessmnemonic.diskord.gateway.GatewayIntents
import java.io.FileReader

fun main(args: Array<String>) {

    System.getProperties().load(FileReader("env.properties"))
    val botToken: String? = System.getProperty("token")

    if (botToken == null) {
        throw Exception("no token specified in environment")
    }

    val http = HttpClient {
        install(JsonFeature)
        install(WebSockets)
    }

    val config = DisKordClientConfig (
        tokenType = TokenType.BOT,
        token = botToken,
        reconnectAttempts = 5,
        intents = GatewayIntents.allOf(
            GatewayIntents.GUILDS,
            GatewayIntents.GUILD_MESSAGES
        )
    )

    val diskord = DisKordClient(http, config)

    /** Note on design principles
     * The Discord client lives for as long as the HttpClient. If the HttpClient is shut down,
     * all coroutines launched by the Discord client ought to shut down as well. However, if the
     * Discord client is shut down, then the HttpClient must not fail
     */
    diskord.launch {
        diskord.connect()
        println("Connected!")
    }
}
