package uselessmnemonic.diskord.gateway.op

object GatewayOpcodes {
    val DISPATCH: Int = 0
    val HEARTBEAT: Int = 1
    val IDENTIFY: Int = 2
    val PRESENCE_UPDATE: Int = 3
    val VOICE_STATE_UPDATE: Int = 4
    val RESUME: Int = 6
    val RECONNECT: Int = 7
    val REQUEST_GUILD_MEMBERS: Int = 8
    val INVALID_SESSION: Int = 9
    val HELLO: Int = 10
    val HEARTBEAT_ACK: Int = 11
}
