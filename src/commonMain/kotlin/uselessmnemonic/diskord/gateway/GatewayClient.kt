package uselessmnemonic.diskord.gateway

import io.ktor.client.features.websocket.*
import io.ktor.http.cio.websocket.*
import io.ktor.util.date.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import uselessmnemonic.diskord.gateway.op.GatewayOps
import uselessmnemonic.diskord.gateway.op.Hello
import uselessmnemonic.diskord.gateway.op.Payload
import uselessmnemonic.diskord.gateway.op.unwrap

class GatewayClient(private var socketSession: DefaultClientWebSocketSession) {

    private var timer: Job? = null
    private var lastSequence: Int? = null

    suspend fun tryConnect() {
        socketSession.incoming.consumeAsFlow()
            .mapNotNull { it as? Frame.Text }
            .map { it.readText() }
            .map { Json.decodeFromString<Payload<JsonElement>>(it) }
            .collect { onPayload(it) }
    }

    private suspend fun onPayload(payload: Payload<JsonElement>) {
        lastSequence = payload.s
        when (payload.op) {
            GatewayOps.HELLO -> onHello(Json.unwrap(payload))
            GatewayOps.HEARTBEAT_ACK -> onHeartbeatACK(payload)
            GatewayOps.
        }
    }

    private fun onHello(hello: Payload<Hello>) {
    }

    private fun onUnknown(decodeFromJsonElement: Hello) {

    }

    // outwards facing commands
    private suspend fun sendString(str: String) {
        socketSession!!.send(str)
    }

    private suspend fun startHeartbeat(period: Long) {
        timer?.cancelAndJoin()
        timer = CoroutineScope(Dispatchers.Main).launch {
            var last = getTimeMillis()
            while (isActive) {
                val now = getTimeMillis()
                if (now - last >= period) {
                    last = now
                    sendHeartbeat()
                }
            }
        }
    }

    private suspend fun sendHeartbeat() {
        sendString("{\"op\": 1,\"d\": $lastSequence}")
    }
}