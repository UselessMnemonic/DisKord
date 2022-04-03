package uselessmnemonic.diskord

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.sync.Mutex
import uselessmnemonic.diskord.events.AsyncEvent
import uselessmnemonic.diskord.events.MessageCreateEventArgs
import uselessmnemonic.diskord.gateway.ApiLevel
import uselessmnemonic.diskord.gateway.GatewayClient
import uselessmnemonic.diskord.rest.entities.user.IUser

class DisKordClient(val httpClient: HttpClient, val config: DisKordClientConfig) {

    // internal objects
    private val gatewayClient = GatewayClient(httpClient, config)

    // state locks
    private val connectionMutex = Mutex()

    // running state
    var connectionState = ConnectionState.DISCONNECTED; private set
    lateinit var currentUser: IUser; private set

    // events
    val messageCreated: AsyncEvent<MessageCreateEventArgs> = AsyncEvent()

    suspend fun connect() {
        if (!connectionMutex.tryLock()) {
            throw IllegalStateException("This client is already connecting.")
        }

        if (connectionState == ConnectionState.CONNECTED) {
            connectionMutex.unlock()
            throw IllegalStateException("This client is already connected.")
        }

        connectionState = ConnectionState.CONNECTING
        var reconnectCounter = config.reconnectAttemps
        var err: Exception? = null

        while (config.reconnectIndefinitely || reconnectCounter-- > 0) try {
            gatewayClient.tryConnect()
            connectionState = ConnectionState.CONNECTED
            break
        } catch (ex: Exception) {
            err = ex
        }

        if (connectionState != ConnectionState.CONNECTED) {
            connectionState = ConnectionState.DISCONNECTED
            connectionMutex.unlock()
            throw Exception("Could not connect to Discord.", err)
        }

        connectionMutex.unlock()
    }

    private val authHeaders: HeadersBuilder.() -> Unit = {
        append(HttpHeaders.Accept, "application/json")
        append(HttpHeaders.UserAgent, "DisKord")
        append(HttpHeaders.Authorization, "${config.tokenType} ${config.token}")
    }

    private val botRequest: HttpRequestBuilder.() -> Unit = {
        headers(authHeaders)
        parameter("v", ApiLevel.V9.code)
        parameter("encoding", "json")
    }

    // socket handlers
    private fun onSocketConnect() {

    }

    private fun onSocketMessage() {

    }

    private fun onSocketDisconnect() {

    }

    // gateway operation handlers
    private fun onHeartbeat() {

    }
}
