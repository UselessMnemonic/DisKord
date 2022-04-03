package uselessmnemonic.diskord.rest.entities.channel.message

import uselessmnemonic.diskord.rest.IUnique
import uselessmnemonic.diskord.rest.Snowflake
import uselessmnemonic.diskord.rest.entities.application.IApplication
import uselessmnemonic.diskord.rest.entities.channel.IChannel
import uselessmnemonic.diskord.rest.entities.channel.IChannelMention
import uselessmnemonic.diskord.rest.entities.channel.embed.IEmbed
import uselessmnemonic.diskord.rest.entities.channel.message.component.IComponent
import uselessmnemonic.diskord.rest.entities.guild.IGuildMember
import uselessmnemonic.diskord.rest.entities.role.IRole
import uselessmnemonic.diskord.rest.entities.sticker.ISticker
import uselessmnemonic.diskord.rest.entities.sticker.IStickerItem
import uselessmnemonic.diskord.rest.entities.user.IUser

interface IMessage: IUnique {
    val channelId: Snowflake
    val guildId: Snowflake?
    val author: IUser
    val member: IGuildMember?
    val content: String
    val timestamp: String
    val editedTimestamp: String?
    val tts: Boolean
    val mentionEveryone: Boolean
    val mentions: Array<IUser>
    val mentionRoles: Array<IRole>
    val mentionChannels: Array<IChannelMention>?
    val attachments: Array<IAttatchment>
    val embeds: Array<IEmbed>
    val reactions: Array<IReaction>?
    val nonce: String?
    val pinned: Boolean
    val webhookId: Snowflake?
    val type: MessageType
    val activity: IMessageActivity?
    val application: IApplication?
    val applicationId: Snowflake?
    val messageReference: IMessageReference?
    val flags: Int
    val referencedMessage: IMessage?
    val interaction: IMessageInteraction?
    val thread: IChannel?
    val components: Array<IComponent>?
    val stickerItems: Array<IStickerItem>
    val stickers: Array<ISticker>
}