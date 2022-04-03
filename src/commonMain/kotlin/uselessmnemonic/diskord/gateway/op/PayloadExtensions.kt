package uselessmnemonic.diskord.gateway.op

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

inline fun <reified T> Json.unwrap(payload: Payload<JsonElement>): Payload<T> {

    val elem = payload.d.let {
        if (it == null) return@let null
        return@let this.decodeFromJsonElement<T>(it)
    }

    return Payload(payload.op, payload.s, payload.t, elem)
}
