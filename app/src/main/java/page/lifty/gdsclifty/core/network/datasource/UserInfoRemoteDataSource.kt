package page.lifty.gdsclifty.core.network.datasource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import page.lifty.gdsclifty.core.common.network.Dispatcher
import page.lifty.gdsclifty.core.common.network.MainDispatchers.IO
import page.lifty.gdsclifty.core.network.ktor.Ktor
import page.lifty.gdsclifty.core.network.model.response.UserInfoResponse
import javax.inject.Inject

interface UserRemoteDataSource {
    val getUserInfo: Flow<UserInfoResponse>
}

/**
 * 네트워크 요청 수행 및 결과 방출
 */
class DefaultUserInfoRemoteDataSource @Inject constructor(
    private val ktor: Ktor,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : UserRemoteDataSource {
    override val getUserInfo: Flow<UserInfoResponse> = flow {
        val userInfo = ktor.getUserInfo()
        emit(userInfo)
    }
        .catch { }
        .flowOn(ioDispatcher)
}