package uselessmnemonic.diskord.rest.entities.channel

import uselessmnemonic.diskord.rest.Snowflake

interface IChannelMention {
    val id: uselessmnemonic.diskord.rest.Snowflake
    val guildId: uselessmnemonic.diskord.rest.Snowflake
    val type: ChannelType
    val name: String
}