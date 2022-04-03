package uselessmnemonic.diskord.rest.entities.application

import uselessmnemonic.diskord.rest.Snowflake
import uselessmnemonic.diskord.rest.entities.guild.ITeam
import uselessmnemonic.diskord.rest.entities.user.IUser

interface IApplication: uselessmnemonic.diskord.rest.IUnique {
    val name: String
    val icon: String?
    val description: String
    val rpcOrigins: Array<String>?
    val botPublic: Boolean
    val botRequireCodeGrant: Boolean?
    val termsOfServiceURL: String?
    val privacyPolicyURL: String?
    val owner: IUser?
    val summary: String
    val verifyKey: String
    val team: ITeam?
    val guildId: Snowflake?
    val primarySkuId: Snowflake?
    val slug: String?
    val coverImage: String?
    val flags: Int?
}
