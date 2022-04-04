import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.websocket.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import uselessmnemonic.diskord.DisKordClient
import uselessmnemonic.diskord.DisKordClientConfig
import uselessmnemonic.diskord.TokenType
import uselessmnemonic.diskord.events.MessageCreateEventArgs
import java.io.FileReader

suspend fun main(args: Array<String>) {

    val client = HttpClient {
        install(JsonFeature)
        install(WebSockets)
    }

    val botToken: String = withContext(Dispatchers.IO) {
        System.getProperties().load(FileReader("env.properties"))
        System.getProperty("discord-token")
    }

    val config = DisKordClientConfig(
        tokenType = TokenType.BOT,
        token = botToken
    )
    val diskord = DisKordClient(client, config)

    diskord.messageCreated += ::onMessageCreated
    diskord.connect()
}

suspend fun onMessageCreated(client: DisKordClient, e: MessageCreateEventArgs) {
    if (e.message.author.id != client.currentUser.id) {
        val msg = e.message.content.trim().lowercase()
        //e.message.respond("${e.author.mention} $msg")
    }
}
