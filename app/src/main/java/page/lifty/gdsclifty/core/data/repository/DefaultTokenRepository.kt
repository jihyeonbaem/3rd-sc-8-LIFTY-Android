package page.lifty.gdsclifty.core.data.repository

import page.lifty.gdsclifty.core.datastore.datasource.DefaultTokenLocalDataSource
import javax.inject.Inject

class DefaultTokenRepository @Inject constructor(
    private val defaultTokenDataSource: DefaultTokenLocalDataSource,
) {
    val getAccessToken = defaultTokenDataSource.getAccessToken
    val getRefreshToken = defaultTokenDataSource.getRefreshToken

    suspend fun saveAccessToken(accessToken: String) =
        defaultTokenDataSource.saveAccessToken(accessToken)

    suspend fun saveRefreshToken(refreshToken: String) =
        defaultTokenDataSource.saveRefreshToken(refreshToken)
}