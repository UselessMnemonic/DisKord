package uselessmnemonic.diskord.rest.entities.channel.message

enum class MessageActivityType(val value: Int) {
    JOIN(1),
    SPECTATE(2),
    LISTEN(3),
    JOIN_REQUEST(5)
}