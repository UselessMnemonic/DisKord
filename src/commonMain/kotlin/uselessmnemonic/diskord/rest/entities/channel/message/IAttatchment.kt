package uselessmnemonic.diskord.rest.entities.channel.message

import uselessmnemonic.diskord.rest.IUnique

interface IAttatchment: uselessmnemonic.diskord.rest.IUnique {
    val filename: String
    val description: String?
    val contentType: String?
    val size: Int
    val url: String
    val proxyUrl: String
    val height: Int?
    val width: Int?
    val ephemeral: Boolean?
}