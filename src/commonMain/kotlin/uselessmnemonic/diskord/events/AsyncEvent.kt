package uselessmnemonic.diskord.events

import io.ktor.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import uselessmnemonic.diskord.DisKordClient

class AsyncEvent<T> {

    private val bus = MutableSharedFlow<Pair<DisKordClient, T>>()
    private val events = bus.asSharedFlow()

    private val mutex = Mutex()
    private val scopes = mutableMapOf<AsyncEventHandler<T>, CoroutineScope>()

    suspend fun add(handler: AsyncEventHandler<T>) {
        val scope = mutex.withLock {
            val scope = CoroutineScope(Dispatchers.Default)
            scopes.put(handler, scope)
        }
        scope?.launch {
            events.collect {
                handler(it.first, it.second)
            }
        }
    }

    suspend fun remove(handler: AsyncEventHandler<T>) {
        val scope = mutex.withLock {
            scopes.remove(handler)
        }
        scope?.cancel()
    }

    suspend fun emit(client: DisKordClient, value: T) = bus.emit(client to value)

    suspend operator fun plusAssign(handler: AsyncEventHandler<T>) = add(handler)
    suspend operator fun minusAssign(handler: AsyncEventHandler<T>) = remove(handler)
    suspend operator fun invoke(client: DisKordClient, args: T) = emit(client, args)
}
