package page.lifty.gdsclifty.core.datastore.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class DefaultTokenLocalDataSource @Inject constructor(
    private val preferencesDataStore: DataStore<Preferences>
) {
    val getRefreshToken: Flow<String?> = preferencesDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            preference[REFRESH_TOKEN_KEY] ?: ""
        }

    suspend fun saveRefreshToken(refreshToken: String) =
        preferencesDataStore.edit { mutablePreferences ->
            mutablePreferences[REFRESH_TOKEN_KEY] = refreshToken
        }

    val getAccessToken = preferencesDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            preference[ACCESS_TOKEN_KEY]
        }

    suspend fun saveAccessToken(accessToken: String) =
        preferencesDataStore.edit { mutablePreferences ->
            mutablePreferences[ACCESS_TOKEN_KEY] = accessToken
        }

    companion object {
        val REFRESH_TOKEN_KEY = stringPreferencesKey(
            name = "refreshToken"
        )

        val ACCESS_TOKEN_KEY = stringPreferencesKey(
            name = "accessToken"
        )
    }
}