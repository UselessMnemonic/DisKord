package uselessmnemonic.diskord.rest.entities.webhook

enum class WebhookType(val value: Int) {
    Incoming(1),
    ChannelFollower(2),
    Application(3)
}