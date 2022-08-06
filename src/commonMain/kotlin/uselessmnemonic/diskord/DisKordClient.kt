package uselessmnemonic.diskord

import io.ktor.client.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import uselessmnemonic.diskord.gateway.GatewayClient
import uselessmnemonic.diskord.rest.RestClient

class DisKordClient(httpClient: HttpClient, val config: DisKordClientConfig): CoroutineScope {

    // coroutine scope for this client
    private val clientScope = CoroutineScope(Dispatchers.Default)
    override val coroutineContext = clientScope.coroutineContext

    // internal clients
    private var gatewayClient = GatewayClient(httpClient, config)
    private var restClient = RestClient(httpClient, config)

    // state locks
    private val connectionMutex = Mutex()

    // running state
    var connectionState = ConnectionState.DISCONNECTED; private set
    // lateinit var currentUser: IUser; private set

    // event flows
    private val _messageCreated = MutableSharedFlow<Nothing>()
    val messageCreated = _messageCreated as SharedFlow<Nothing>

    /**
     * Connects the client to Discord.
     * Upon success, a handshake process begins to start or resume the bot session.
     * If the connection fails, or too many attempts are made, an exception is thrown.
     * @throws Exception
     */
    suspend fun connect() {
        if (!connectionMutex.tryLock()) {
            throw IllegalStateException("this client is already connecting")
        }

        if (connectionState == ConnectionState.CONNECTED) {
            connectionMutex.unlock()
            throw IllegalStateException("this client is already connected")
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
            throw Exception("could not connect to Discord", err)
        }

        connectionMutex.unlock()
    }
}
