package page.lifty.gdsclifty.core.network.datasource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import page.lifty.gdsclifty.core.common.network.Dispatcher
import page.lifty.gdsclifty.core.common.network.MainDispatchers.IO
import page.lifty.gdsclifty.core.network.ktor.Ktor
import page.lifty.gdsclifty.core.network.model.response.DiaryResponse
import javax.inject.Inject

interface DiaryRemoteDataSource {
    val getDiary: Flow<DiaryResponse>
}

class DefaultDiaryRemoteDataSource @Inject constructor(
    private val ktor: Ktor,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
) : DiaryRemoteDataSource {
    override val getDiary: Flow<DiaryResponse> = flow {
        val diary = ktor.getDiary()
        emit(diary)
    }.flowOn(ioDispatcher)
}