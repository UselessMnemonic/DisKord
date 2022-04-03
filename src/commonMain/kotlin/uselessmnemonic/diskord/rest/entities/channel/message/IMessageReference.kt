package uselessmnemonic.diskord.rest.entities.channel.message

import uselessmnemonic.diskord.rest.Snowflake

interface IMessageReference {
    val messageId: uselessmnemonic.diskord.rest.Snowflake?
    val channelId: uselessmnemonic.diskord.rest.Snowflake?
    val guildId: uselessmnemonic.diskord.rest.Snowflake?
    val failIfNotExists: Boolean?
}