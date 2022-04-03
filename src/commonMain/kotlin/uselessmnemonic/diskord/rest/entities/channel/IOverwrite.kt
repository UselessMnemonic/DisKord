package uselessmnemonic.diskord.rest.entities.channel

import uselessmnemonic.diskord.rest.Snowflake

interface IOverwrite {
    val id: uselessmnemonic.diskord.rest.Snowflake
    val type: OverwriteType
    val allow: String
    val deny: String
}