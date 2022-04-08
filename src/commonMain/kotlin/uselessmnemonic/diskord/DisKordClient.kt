package uselessmnemonic.diskord

import io.ktor.client.*
import kotlinx.coroutines.sync.Mutex
import uselessmnemonic.diskord.events.AsyncEvent
import uselessmnemonic.diskord.events.MessageCreateEventArgs
import uselessmnemonic.diskord.gateway.GatewayClient
import uselessmnemonic.diskord.rest.RestClient
import uselessmnemonic.diskord.rest.entities.user.IUser

class DisKordClient(val httpClient: HttpClient, val config: DisKordClientConfig) {

    // internal clients
    private var gatewayClient = GatewayClient(httpClient, config)
    private var restClient = RestClient(httpClient, config)

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
        var reconnectCounter = config.reconnectAttempts
        var err: Exception? = null

        val gateway = restClient.getGatewayBot()
        while (config.reconnectIndefinitely || reconnectCounter-- > 0) try {
            gatewayClient.connect(gateway.url)
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
}
