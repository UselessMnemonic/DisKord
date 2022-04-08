import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import uselessmnemonic.diskord.DisKordClient
import uselessmnemonic.diskord.DisKordClientConfig
import uselessmnemonic.diskord.TokenType
import uselessmnemonic.diskord.events.MessageCreateEventArgs
import uselessmnemonic.diskord.gateway.GatewayClient
import uselessmnemonic.diskord.gateway.GatewayIntents
import java.io.FileReader

suspend fun main(args: Array<String>) {

    val client = HttpClient {
        install(JsonFeature)
        install(WebSockets)
    }

    val botToken: String = withContext(Dispatchers.IO) {
        System.getProperties().load(FileReader("env.properties"))
        System.getProperty("token")
    }

    val config = DisKordClientConfig(
        tokenType = TokenType.BOT,
        token = botToken,
        reconnectAttempts = 1,
        intents = GatewayIntents.allOf(
            GatewayIntents.GUILDS,
            GatewayIntents.GUILD_MESSAGES
        )
    )
    val diskord = DisKordClient(client, config)

    diskord.messageCreated += ::onMessageCreated
    diskord.connect()

    println("Connected!")
    awaitCancellation()
}

suspend fun onMessageCreated(client: DisKordClient, e: MessageCreateEventArgs) {
    /*if (e.message.author.id != client.currentUser.id) {
        val msg = e.message.content.trim().lowercase()
        //e.message.respond("${e.author.mention} $msg")
    }*/
}
