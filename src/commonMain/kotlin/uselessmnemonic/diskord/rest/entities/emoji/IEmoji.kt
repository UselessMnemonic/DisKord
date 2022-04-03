package uselessmnemonic.diskord.rest.entities.emoji

import uselessmnemonic.diskord.rest.IUnique
import uselessmnemonic.diskord.rest.Snowflake
import uselessmnemonic.diskord.rest.entities.user.IUser

interface IEmoji: IUnique {
    val name: String?
    val roles: Array<Snowflake>?
    val user: IUser?
    val requireColons: Boolean?
    val managed: Boolean?
    val animated: Boolean?
    val available: Boolean?
}