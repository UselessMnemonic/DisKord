package uselessmnemonic.diskord.rest.entities.channel

import uselessmnemonic.diskord.rest.Snowflake

interface IAllowedMentions {
    val parse: Array<AllowedMentionTypes>
    val roles: Array<uselessmnemonic.diskord.rest.Snowflake>
    val users: Array<uselessmnemonic.diskord.rest.Snowflake>
    val repliedUser: Boolean
}