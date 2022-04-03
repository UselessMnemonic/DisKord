package uselessmnemonic.diskord.rest.entities.user

import uselessmnemonic.diskord.rest.IUnique
import uselessmnemonic.diskord.rest.entities.guild.IIntegration

interface IConnection: IUnique {
    val name: String
    val type: String
    val revoked: Boolean?
    val integrations: Array<IIntegration>?
    val verified: Boolean
    val friendSync: Boolean
    val showActivity: Boolean
    val visibility: Int
}