package page.lifty.gdsclifty.core.common.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import page.lifty.gdsclifty.core.common.network.Dispatcher
import page.lifty.gdsclifty.core.common.network.MainDispatchers.Default
import page.lifty.gdsclifty.core.common.network.MainDispatchers.IO

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    /**
     * IO 작업을 수행하는 CoroutineDispatcher 제공
     */
    @Provides
    @Dispatcher(IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    /**
     * 기본 CoroutineDispatcher 제공
     */
    @Provides
    @Dispatcher(Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}