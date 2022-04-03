package uselessmnemonic.diskord.rest

import kotlinx.serialization.Serializable

@Serializable
class Snowflake(val value: String): IUnique {

    override val id: Snowflake = this

    val discordEpoch: Long
        get() = value.substring(0, 42).toLong(2)

    val unixEpoch: Long
        get() = discordEpoch + 1420070400000L

    override fun equals(other: Any?): Boolean {
        return if (other is uselessmnemonic.diskord.rest.Snowflake) {
            equals(other)
        } else false
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun equals(other: Snowflake): Boolean {
        return other.value == this.value
    }
}