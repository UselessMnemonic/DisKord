package uselessmnemonic.diskord.rest.entities.channel.message

interface IMessageActivity {
    val type: MessageActivityType
    val partyId: String?
}