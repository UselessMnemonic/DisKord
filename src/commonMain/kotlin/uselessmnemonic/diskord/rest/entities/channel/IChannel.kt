package uselessmnemonic.diskord.rest.entities.channel

import uselessmnemonic.diskord.rest.IUnique
import uselessmnemonic.diskord.rest.Snowflake
import uselessmnemonic.diskord.rest.entities.user.IUser

interface IChannel: IUnique {
    val type: ChannelType
    val guildId: Snowflake?
    val position: Int?
    val permissionOverwrites: Array<IOverwrite>?
    val name: String?
    val topic: String?
    val nsfw: Boolean?
    val lastMessageId: Snowflake?
    val bitrate: Int?
    val userLimit: Int?
    val rateLimitPerUser: Int?
    val recipients: Array<IUser>?
    val icon: String?
    val ownerId: Snowflake?
    val applicationId: Snowflake?
    val parentId: Snowflake?
    val lastPinTimestamp: String?
    val rtcRegion: String?
    val videoQualityMode: VideoQualityMode?
    val messageCount: Int?
    val memberCount: Int?
    val threadMetadata: IThreadMetadata?
    val member: IThreadMember?
    val defaultAutoArchiveDuration: Int?
    val permissions: String?
}