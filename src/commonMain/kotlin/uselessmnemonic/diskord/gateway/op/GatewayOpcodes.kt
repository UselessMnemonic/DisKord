package uselessmnemonic.diskord.gateway.op

object GatewayOpcodes {
    const val DISPATCH: Int = 0
    const val HEARTBEAT: Int = 1
    const val IDENTIFY: Int = 2
    const val PRESENCE_UPDATE: Int = 3
    const val VOICE_STATE_UPDATE: Int = 4
    const val RESUME: Int = 6
    const val RECONNECT: Int = 7
    const val REQUEST_GUILD_MEMBERS: Int = 8
    const val INVALID_SESSION: Int = 9
    const val HELLO: Int = 10
    const val HEARTBEAT_ACK: Int = 11
}
