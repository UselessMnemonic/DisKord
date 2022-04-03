package uselessmnemonic.diskord.rest.entities.auditlog

import uselessmnemonic.diskord.rest.entities.webhook.IWebhook
import uselessmnemonic.diskord.rest.entities.channel.IChannel
import uselessmnemonic.diskord.rest.entities.guild.IIntegration
import uselessmnemonic.diskord.rest.entities.user.IUser

interface IAuditLog {
    val auditLogEntries: Array<IAuditLogEntry>
    val integrations: Array<IIntegration>
    val threads: Array<IChannel>
    val users: Array<IUser>
    val webhooks: Array<IWebhook>
}