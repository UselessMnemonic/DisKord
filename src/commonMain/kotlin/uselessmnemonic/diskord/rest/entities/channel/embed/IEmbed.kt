package uselessmnemonic.diskord.rest.entities.channel.embed

interface IEmbed {
    val title: String?
    val type: EmbedType?
    val description: String?
    val url: String?
    val timestamp: String?
    val color: Int?
    val footer: IEmbedFooter?
    val image: IEmbedImage?
    val thumbnail: IEmbedThumbnail?
    val video: IEmbedVideo?
    val provider: IEmbedProvider?
    val author: IEmbedAuthor?
    val fields: Array<IEmbedField>?
}