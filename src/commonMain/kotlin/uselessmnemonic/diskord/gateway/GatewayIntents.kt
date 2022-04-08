package uselessmnemonic.diskord.gateway

object GatewayIntents {
    val GUILDS: Int = 1 shl 0
    val GUILD_MEMBERS: Int = 1 shl 1
    val GUILD_BANS: Int = 1 shl 2
    val GUILD_EMOJIS_AND_STICKERS: Int = 1 shl 3
    val GUILD_INTEGRATIONS: Int = 1 shl 4
    val GUILD_WEBHOOKS: Int = 1 shl 5
    val GUILD_INVITES: Int = 1 shl 6
    val GUILD_VOICE_STATES: Int = 1 shl 7
    val GUILD_PRESENCES: Int = 1 shl 8
    val GUILD_MESSAGES: Int = 1 shl 9
    val GUILD_MESSAGE_REACTIONS: Int = 1 shl 10
    val GUILD_MESSAGE_TYPING: Int = 1 shl 11
    val DIRECT_MESSAGES: Int = 1 shl 12
    fun allOf(vararg intents: Int): Int {
        return intents.reduce(Int::or)
    }
}
