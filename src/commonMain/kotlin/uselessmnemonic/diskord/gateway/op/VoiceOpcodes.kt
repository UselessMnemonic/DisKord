package uselessmnemonic.diskord.gateway.op

object VoiceOpcodes {
    val IDENTIFY: Int = 0
    val SELECT_PROTOCOL: Int = 1
    val READY: Int = 2
    val HEARTBEAT: Int = 3
    val SESSION_DESCRIPTION: Int = 4
    val SPEAKING: Int = 5
    val HEARTBEAT_ACK: Int = 6
    val RESUME: Int = 7
    val HELLO: Int = 8
    val RESUMED: Int = 9
    val CLIENT_DISCONNECT: Int = 13
}
