package page.lifty.gdsclifty.core.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import page.lifty.gdsclifty.core.common.network.Dispatcher
import page.lifty.gdsclifty.core.common.network.MainDispatchers.Default
import page.lifty.gdsclifty.core.network.datasource.DefaultUserRemoteDataSource
import page.lifty.gdsclifty.core.network.model.response.UserResponse
import javax.inject.Inject

class DefaultUserRepository @Inject constructor(
    private val defaultUserRemoteDataSource: DefaultUserRemoteDataSource,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher,
) {
    val getUser: Flow<UserResponse> = defaultUserRemoteDataSource.getUser
        .map { it }
        .onEach { }
        .flowOn(defaultDispatcher)
        .catch { }
}