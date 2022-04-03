package uselessmnemonic.diskord.rest.entities.channel.embed

interface IEmbedImage {
    val url: String
    val proxyUrl: String?
    val height: Int?
    val width: Int?
}