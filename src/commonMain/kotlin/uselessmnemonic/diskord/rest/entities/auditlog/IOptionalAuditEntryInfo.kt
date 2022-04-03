package uselessmnemonic.diskord.rest.entities.auditlog

import uselessmnemonic.diskord.rest.Snowflake

interface IOptionalAuditEntryInfo {
    val channelId: uselessmnemonic.diskord.rest.Snowflake
    val count: String
    val deleteMemberDays: String
    val id: uselessmnemonic.diskord.rest.Snowflake
    val membersMoved: String
    val messageId: uselessmnemonic.diskord.rest.Snowflake
    val roleName: String
    val type: String
}
