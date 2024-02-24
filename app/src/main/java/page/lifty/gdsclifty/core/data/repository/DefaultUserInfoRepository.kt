package page.lifty.gdsclifty.core.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import page.lifty.gdsclifty.core.common.network.Dispatcher
import page.lifty.gdsclifty.core.common.network.MainDispatchers.Default
import page.lifty.gdsclifty.core.domain.repository.UserInfoRepository
import page.lifty.gdsclifty.core.network.datasource.UserRemoteDataSource
import page.lifty.gdsclifty.core.network.model.response.UserInfoResponse
import timber.log.Timber
import javax.inject.Inject

class DefaultUserInfoRepository @Inject constructor(
    userInfoRemoteDataSource: UserRemoteDataSource,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher,
) : UserInfoRepository {
    override val getUserInfo: Flow<UserInfoResponse> = userInfoRemoteDataSource.getUserInfo
        .map { it }
        .onEach { userInfoResponse ->
            Timber.d("${userInfoResponse.code} ${userInfoResponse.message}")
        }
        .catch { exception ->
            Timber.d("${exception.message}")
        }
        .flowOn(defaultDispatcher)
}