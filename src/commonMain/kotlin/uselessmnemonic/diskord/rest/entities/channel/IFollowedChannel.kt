package uselessmnemonic.diskord.rest.entities.channel

import uselessmnemonic.diskord.rest.Snowflake

interface IFollowedChannel {
    val channelId: uselessmnemonic.diskord.rest.Snowflake
    val webhookId: uselessmnemonic.diskord.rest.Snowflake
}