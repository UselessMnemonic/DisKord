package uselessmnemonic.diskord.rest.entities.auditlog

import uselessmnemonic.diskord.rest.IUnique
import uselessmnemonic.diskord.rest.Snowflake

interface IAuditLogEntry: uselessmnemonic.diskord.rest.IUnique {
    val targetId: String?
    val changes: Array<IAuditLogChange>?
    val userId: uselessmnemonic.diskord.rest.Snowflake?
    val actionType: AuditLogEvent
    val options: IOptionalAuditEntryInfo?
    val reason: String?
}