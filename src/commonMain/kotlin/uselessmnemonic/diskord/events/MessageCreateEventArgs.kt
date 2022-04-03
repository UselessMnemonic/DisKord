package uselessmnemonic.diskord.events

import uselessmnemonic.diskord.rest.entities.channel.message.IMessage
import uselessmnemonic.diskord.rest.entities.user.IUser

class MessageCreateEventArgs {
    val author: IUser
    val message: IMessage
}
