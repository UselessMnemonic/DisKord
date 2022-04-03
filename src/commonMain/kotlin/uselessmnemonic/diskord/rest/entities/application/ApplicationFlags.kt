package uselessmnemonic.diskord.rest.entities.application

enum class ApplicationFlags(val bit: Int) {
    GATEWAY_PRESENCE(1 shl 12),
    GATEWAY_PRESENCE_LIMITED(1 shl 13),
    GATEWAY_GUILD_MEMBERS(1 shl 14),
    GATEWAY_GUILD_MEMBERS_LIMITED(1 shl 15),
    VERIFICATION_PENDING_GUILD_LIMIT(1 shl 16),
    EMBEDDED(1 shl 17),
    GATEWAY_MESSAGE_CONTENT(1 shl 18),
    GATEWAY_MESSAGE_CONTENT_LIMITED(1 shl 19),
}