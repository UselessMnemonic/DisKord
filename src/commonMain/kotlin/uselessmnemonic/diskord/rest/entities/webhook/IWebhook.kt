package uselessmnemonic.diskord.rest.entities.webhook

import uselessmnemonic.diskord.rest.IUnique
import uselessmnemonic.diskord.rest.Snowflake
import uselessmnemonic.diskord.rest.entities.channel.IChannel
import uselessmnemonic.diskord.rest.entities.guild.IGuild
import uselessmnemonic.diskord.rest.entities.user.IUser

interface IWebhook: IUnique {
    val type: WebhookType
    val guildId: Snowflake?
    val channelId: Snowflake?
    val user: IUser?
    val name: String?
    val avatar: String?
    val token: String?
    val applicationId: Snowflake?
    val sourceGuild: IGuild?
    val sourceChannel: IChannel?
    val url: String?
}