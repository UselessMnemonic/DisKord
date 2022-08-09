package uselessmnemonic.diskord

import io.ktor.client.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.sync.Mutex
import uselessmnemonic.diskord.gateway.GatewayClient
import uselessmnemonic.diskord.rest.RestClient
import kotlin.coroutines.CoroutineContext

class DisKordClient(httpClient: HttpClient, val config: DisKordClientConfig): CoroutineScope {

    override val coroutineContext: CoroutineContext = httpClient.coroutineContext + Job()

    // internal clients
    private var gateway = GatewayClient(httpClient, config)
    private var rest = RestClient(httpClient, config)

    // state locks
    private val connectionMutex = Mutex()

    // running state
    var connectionState = ConnectionState.DISCONNECTED; private set
    // lateinit var currentUser: IUser; private set

    // events
    private val eventJob: Job
    private val eventSource = MutableSharedFlow<String>()
    val events = eventSource.asSharedFlow()

    init {
        eventJob = httpClient.launch {
            while (isActive) {
                val e = gateway.events.receive()
                eventSource.emit(e)
            }
        }
    }

    /**
     * Connects the client to Discord.
     * Upon success, a handshake process begins to start or resume the bot session.
     * If the connection fails, or too many attempts are made, an exception is thrown.
     * @throws Exception
     */
    suspend fun connect() {
        if (connectionState == ConnectionState.CONNECTED) {
            throw IllegalStateException("this client is already connected")
        }

        if (!connectionMutex.tryLock()) {
            throw IllegalStateException("this client is already connecting")
        }

        connectionState = ConnectionState.CONNECTING
        var reconnectCounter = config.reconnectAttempts
        var err: Exception? = null

        val gateway = rest.getGatewayBot()
        while (config.reconnectIndefinitely || reconnectCounter-- > 0) try {
            this.gateway.connect(gateway.url)
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
