package uselessmnemonic.diskord.gateway.op

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

inline fun <reified T> Json.unwrapPacket(payload: Packet<out JsonElement>): T {
    return decodeFromJsonElement(payload.d!!)
}

inline fun Json.decodePacket(str: String): Packet<JsonElement> {
    return decodeFromString(str)
}
