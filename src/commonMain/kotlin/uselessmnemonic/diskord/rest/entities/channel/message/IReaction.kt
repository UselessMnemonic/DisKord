package uselessmnemonic.diskord.rest.entities.channel.message

import uselessmnemonic.diskord.rest.entities.emoji.IEmoji

interface IReaction {
    val count: Int
    val me: Boolean
    val emoji: IEmoji
}