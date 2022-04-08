package uselessmnemonic.diskord.gateway.op

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

inline fun <reified T> Json.unwrap(payload: Payload<JsonElement>): T {
    return decodeFromJsonElement(payload.d!!)
}

inline fun Json.decodePayload(str: String): Payload<JsonElement> {
    return decodeFromString(str)
}
