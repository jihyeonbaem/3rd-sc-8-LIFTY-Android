package page.lifty.gdsclifty.core.network.datasource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import page.lifty.gdsclifty.core.common.network.Dispatcher
import page.lifty.gdsclifty.core.common.network.MainDispatchers.IO
import page.lifty.gdsclifty.core.network.ktor.Ktor
import page.lifty.gdsclifty.core.network.model.response.UserResponse
import javax.inject.Inject

//interface UserRemoteDataSource {
//    val getUser: Flow<UserResponse>
//}

class DefaultUserRemoteDataSource @Inject constructor(
    private val ktor: Ktor,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) {
    val getUser: Flow<UserResponse> = flow {
        val gew = ktor.getUser()
        emit(gew)
    }.flowOn(ioDispatcher)
}