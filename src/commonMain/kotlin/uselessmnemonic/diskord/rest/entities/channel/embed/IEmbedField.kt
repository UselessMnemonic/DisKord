package uselessmnemonic.diskord.rest.entities.channel.embed

interface IEmbedField {
    val name: String
    val value: String
    val inline: Boolean?
}