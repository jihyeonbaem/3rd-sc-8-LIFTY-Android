package page.lifty.gdsclifty.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import page.lifty.gdsclifty.BuildConfig
import page.lifty.gdsclifty.core.common.network.Dispatcher
import page.lifty.gdsclifty.core.common.network.MainDispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun providePreferencesDataStore(
        @ApplicationContext context: Context,
        @Dispatcher(MainDispatchers.IO) ioDispatcher: CoroutineDispatcher,
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler {
                it.printStackTrace()
                emptyPreferences()
            },
            scope = CoroutineScope(ioDispatcher + SupervisorJob()),
            produceFile = {
                context.preferencesDataStoreFile(BuildConfig.APPLICATION_ID)
            }
        )
}