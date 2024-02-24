package page.lifty.gdsclifty.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetWorkModule {
    @Singleton
    @Provides
    fun provideCIOClient(): HttpClientEngine = CIO.create()
}