package uselessmnemonic.diskord.gateway

import io.ktor.client.*
import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.selects.select

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

import uselessmnemonic.diskord.DisKordClientConfig
import uselessmnemonic.diskord.gateway.op.*

class GatewayClient(val httpClient: HttpClient, val config: DisKordClientConfig) {

    private val json = Json {
        ignoreUnknownKeys = true
    }
    private var lastSequence: Int? = null
    private var pendingAcks = 0

    public val events: ReceiveChannel<String> = Channel()

    suspend fun ClientWebSocketSession.closeWithError(code: CloseReason.Codes, msg: String): Nothing {
        close(CloseReason(code, msg))
        throw IllegalStateException(msg)
    }

    suspend fun connect(endpoint: String) {
        val requestBuilder = config.newWebsocketRequest(endpoint)
        val session = httpClient.webSocketSession(requestBuilder)

        // take incoming packets as payloads
        val packets = session.producePackets()

        // perform handshake
        val first = packets.receive()
        if (first.op != GatewayOpcodes.HELLO) {
            session.closeWithError(CloseReason.Codes.PROTOCOL_ERROR, "first gateway opcode was ${first.op}")
        }

        // create heartbeat
        val period = json.unwrapPacket<Hello>(first).heartbeatInterval
        val heartbeat = session.produceHeartbeat(period.toLong())

        // pass events
        session.processEvents(packets, heartbeat)
        session.sendPacket(GatewayOpcodes.IDENTIFY, config.makeIdentity())
    }

    /**
     * Launches a producer that funnels gateway events to the main event source.
     * @receiver A WebSocketSession that produces events.
     * @return A
     */
    private fun WebSocketSession.processEvents(packets: ReceiveChannel<Packet<JsonElement>>, heartbeat: ReceiveChannel<Nothing?>) = launch {
        while (isActive) select<Unit> {
            packets.onReceive {
                this@processEvents.onPacket(it)
            }
            heartbeat.onReceive {
                this@processEvents.doHeartbeat()
            }
        }
    }

    /* OPERATION HANDLERS */
    private suspend inline fun WebSocketSession.onPacket(packet: Packet<JsonElement>) {
        println("Got packet: $packet")
        when (packet.op) {
            GatewayOpcodes.HELLO -> onHello(json.unwrapPacket(packet))
            GatewayOpcodes.DISPATCH -> onDispatch(packet)
            GatewayOpcodes.HEARTBEAT -> doHeartbeat()
            //GatewayOpcodes.IDENTIFY -> throw IllegalStateException()
            //GatewayOpcodes.PRESENCE_UPDATE -> throw IllegalStateException()
            //GatewayOpcodes.VOICE_STATE_UPDATE -> throw IllegalStateException()
            //GatewayOpcodes.RESUME -> throw IllegalStateException()
            GatewayOpcodes.RECONNECT -> onReconnect()
            //GatewayOpcodes.REQUEST_GUILD_MEMBERS -> throw IllegalStateException()
            GatewayOpcodes.INVALID_SESSION -> onInvalidSession()
            GatewayOpcodes.HEARTBEAT_ACK -> onHeartbeatAck()
        }
    }

    private suspend inline fun WebSocketSession.doHeartbeat() {
        // TODO check for zombie connections
        sendPacket(GatewayOpcodes.HEARTBEAT)
        //pendingAcks++
    }

    private inline fun WebSocketSession.onHello(packet: Packet<Hello>) {
        TODO("onHello")
    }

    private inline fun WebSocketSession.onHeartbeat() {
        TODO("onHeartbeat")
    }

    private inline fun WebSocketSession.onReconnect() {
        TODO("onReconnect")
    }

    private inline fun WebSocketSession.onInvalidSession() {
        TODO("onInvalidSession")
    }

    private inline fun WebSocketSession.onHeartbeatAck() {
        pendingAcks = 0
    }

    private suspend inline fun WebSocketSession.onDispatch(payload: Packet<out JsonElement>) {
        lastSequence = payload.s!!
        (events as Channel).send(payload.d?.toString() ?: "(empty event)")
        /* when (payload.t!!) {
            DispatchCodes.READY -> TODO("READY")
            DispatchCodes.RESUMED -> TODO("RESUMED")
            DispatchCodes.INVALID_SESSION -> TODO("INVALID_SESSION")
            DispatchCodes.CHANNEL_CREATE -> TODO("CHANNEL_CREATE")
            DispatchCodes.CHANNEL_UPDATE -> TODO("CHANNEL_UPDATE")
            DispatchCodes.CHANNEL_DELETE -> TODO("CHANNEL_DELETE")
            DispatchCodes.CHANNEL_PINS_UPDATE -> TODO("CHANNEL_PINS_UPDATE")
            DispatchCodes.THREAD_CREATE -> TODO("THREAD_CREATE")
            DispatchCodes.THREAD_UPDATE -> TODO("THREAD_UPDATE")
            DispatchCodes.THREAD_DELETE -> TODO("THREAD_DELETE")
            DispatchCodes.THREAD_LIST_SYNC -> TODO("THREAD_LIST_SYNC")
            DispatchCodes.THREAD_MEMBER_UPDATE -> TODO("THREAD_MEMBER_UPDATE")
            DispatchCodes.THREAD_MEMBERS_UPDATE -> TODO("THREAD_MEMBERS_UPDATE")
            DispatchCodes.GUILD_CREATE -> TODO("GUILD_CREATE")
            DispatchCodes.GUILD_UPDATE -> TODO("GUILD_UPDATE")
            DispatchCodes.GUILD_DELETE -> TODO("GUILD_DELETE")
            DispatchCodes.GUILD_BAN_ADD -> TODO("GUILD_BAN_ADD")
            DispatchCodes.GUILD_BAN_REMOVE -> TODO("GUILD_BAN_REMOVE")
            DispatchCodes.GUILD_EMOJIS_UPDATE -> TODO("GUILD_EMOJIS_UPDATE")
            DispatchCodes.GUILD_STICKERS_UPDATE -> TODO("GUILD_STICKERS_UPDATE")
            DispatchCodes.GUILD_INTEGRATIONS_UPDATE -> TODO("GUILD_INTEGRATIONS_UPDATE")
            DispatchCodes.GUILD_MEMBER_ADD -> TODO("GUILD_MEMBER_ADD")
            DispatchCodes.GUILD_MEMBER_REMOVE -> TODO("GUILD_MEMBER_REMOVE")
            DispatchCodes.GUILD_MEMBER_UPDATE -> TODO("GUILD_MEMBER_UPDATE")
            DispatchCodes.GUILD_MEMBERS_CHUNK -> TODO("GUILD_MEMBERS_CHUNK")
            DispatchCodes.GUILD_ROLE_CREATE -> TODO("GUILD_ROLE_CREATE")
            DispatchCodes.GUILD_ROLE_UPDATE -> TODO("GUILD_ROLE_UPDATE")
            DispatchCodes.GUILD_ROLE_DELETE -> TODO("GUILD_ROLE_DELETE")
            DispatchCodes.GUILD_SCHEDULED_EVENT_CREATE -> TODO("GUILD_SCHEDULED_EVENT_CREATE")
            DispatchCodes.GUILD_SCHEDULED_EVENT_UPDATE -> TODO("GUILD_SCHEDULED_EVENT_UPDATE")
            DispatchCodes.GUILD_SCHEDULED_EVENT_DELETE -> TODO("GUILD_SCHEDULED_EVENT_DELETE")
            DispatchCodes.GUILD_SCHEDULED_EVENT_USER_ADD -> TODO("GUILD_SCHEDULED_EVENT_USER_ADD")
            DispatchCodes.GUILD_SCHEDULED_EVENT_USER_REMOVE -> TODO("GUILD_SCHEDULED_EVENT_USER_REMOVE")
            DispatchCodes.INTEGRATION_CREATE -> TODO("INTEGRATION_CREATE")
            DispatchCodes.INTEGRATION_UPDATE -> TODO("INTEGRATION_UPDATE")
            DispatchCodes.INTEGRATION_DELETE -> TODO("INTEGRATION_DELETE")
            DispatchCodes.INTERACTION_CREATE -> TODO("INTERACTION_CREATE")
            DispatchCodes.INVITE_CREATE -> TODO("INVITE_CREATE")
            DispatchCodes.INVITE_DELETE -> TODO("INVITE_DELETE")
            DispatchCodes.MESSAGE_CREATE -> TODO("MESSAGE_CREATE")
            DispatchCodes.MESSAGE_UPDATE -> TODO("MESSAGE_UPDATE")
            DispatchCodes.MESSAGE_DELETE -> TODO("MESSAGE_DELETE")
            DispatchCodes.MESSAGE_DELETE_BULK -> TODO("MESSAGE_DELETE_BULK")
            DispatchCodes.MESSAGE_REACTION_ADD -> TODO("MESSAGE_REACTION_ADD")
            DispatchCodes.MESSAGE_REACTION_REMOVE -> TODO("MESSAGE_REACTION_REMOVE")
            DispatchCodes.MESSAGE_REACTION_REMOVE_ALL -> TODO("MESSAGE_REACTION_REMOVE_ALL")
            DispatchCodes.MESSAGE_REACTION_REMOVE_EMOJI -> TODO("MESSAGE_REACTION_REMOVE_EMOJI")
            DispatchCodes.PRESENCE_UPDATE -> TODO("PRESENCE_UPDATE")
            DispatchCodes.STAGE_INSTANCE_CREATE -> TODO("STAGE_INSTANCE_CREATE")
            DispatchCodes.STAGE_INSTANCE_DELETE -> TODO("STAGE_INSTANCE_DELETE")
            DispatchCodes.STAGE_INSTANCE_UPDATE -> TODO("STAGE_INSTANCE_UPDATE")
            DispatchCodes.TYPING_START -> TODO("TYPING_START")
            DispatchCodes.USER_UPDATE -> TODO("USER_UPDATE")
            DispatchCodes.VOICE_STATE_UPDATE -> TODO("VOICE_STATE_UPDATE")
            DispatchCodes.VOICE_SERVER_UPDATE -> TODO("VOICE_SERVER_UPDATE")
            DispatchCodes.WEBHOOKS_UPDATE -> TODO("WEBHOOKS_UPDATE")
            else -> println("Got: $payload")
        }*/
    }

    /* DISPATCH HANDLERS */

    /* SESSION HELPERS */

    /**
     * Creates a channel that produces Payloads from incoming text frames.
     * Should be called once for each session.
     * @receiver A WebSocketSession connected to a gatewaty.
     * @return A Channel<Payload>
     */
    private fun WebSocketSession.producePackets() = produce {
        for (frame in incoming) if (frame is Frame.Text) {
            val text = frame.readText()
            val packet = json.decodePacket(text)
            send(packet)
        }
        close()
    }

    /**
     * Launches a ticker channel that produces heartbeat events.
     * Should be called once for each session.
     * @receiver A WebSocketSession connected to a gateway.
     * @returns A Channel<Nothing?> representing the internal heartbeat.
     */
    private fun WebSocketSession.produceHeartbeat(period: Long) = produce {
        while (isActive) {
            delay(period)
            send(null)
        }
        close()
    }

    private suspend inline fun <reified T> WebSocketSession.sendPacket(op: Int, data: T) {
        val packet = Packet(op, data)
        println("Sending packet: $packet")
        send(json.encodeToString(packet))
    }

    private suspend inline fun WebSocketSession.sendPacket(op: Int) {
        val packet = Packet(op, null)
        println("Sending packet: $packet")
        send(json.encodeToString(packet))
    }
}
