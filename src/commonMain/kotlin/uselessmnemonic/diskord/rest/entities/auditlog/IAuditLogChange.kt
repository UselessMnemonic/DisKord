package uselessmnemonic.diskord.rest.entities.auditlog

interface IAuditLogChange {
    val newValue: Any?
    val oldValue: Any?
    val key: AuditLogChangeKey
}
