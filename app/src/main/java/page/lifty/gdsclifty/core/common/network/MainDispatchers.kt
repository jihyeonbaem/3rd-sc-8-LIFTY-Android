package page.lifty.gdsclifty.core.common.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val mainDispatcher: MainDispatchers)

enum class MainDispatchers {
    Default,
    IO,
}