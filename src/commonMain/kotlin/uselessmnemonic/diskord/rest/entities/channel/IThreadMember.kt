package uselessmnemonic.diskord.rest.entities.channel

import uselessmnemonic.diskord.rest.Snowflake

interface IThreadMember {
    val id: uselessmnemonic.diskord.rest.Snowflake?
    val userId: uselessmnemonic.diskord.rest.Snowflake?
    val joinTimestamp: String
    val flags: Int
}