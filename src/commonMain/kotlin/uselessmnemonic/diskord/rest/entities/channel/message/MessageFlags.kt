package uselessmnemonic.diskord.rest.entities.channel.message

enum class MessageFlags(val bit: Int) {
    CROSSPOSTED(1),
    IS_CROSSPOST(1 shl 1),
    SUPPRESS_EMBEDS(1 shl 2),
    SOURCE_MESSAGE_DELETED(1 shl 3),
    URGENT(1 shl 4),
    HAS_THREAD(1 shl 5),
    EPHEMERAL(1 shl 6),
    LOADING(1 shl 7)
}