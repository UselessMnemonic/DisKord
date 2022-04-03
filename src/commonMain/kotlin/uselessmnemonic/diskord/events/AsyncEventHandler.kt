package uselessmnemonic.diskord.events

import uselessmnemonic.diskord.DisKordClient

typealias AsyncEventHandler<T> = suspend (DisKordClient, T) -> Unit
