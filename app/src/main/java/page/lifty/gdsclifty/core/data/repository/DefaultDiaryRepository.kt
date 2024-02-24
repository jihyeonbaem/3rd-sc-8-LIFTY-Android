package page.lifty.gdsclifty.core.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import page.lifty.gdsclifty.core.common.network.Dispatcher
import page.lifty.gdsclifty.core.common.network.MainDispatchers.Default
import page.lifty.gdsclifty.core.domain.repository.DiaryRepository
import page.lifty.gdsclifty.core.network.datasource.DiaryRemoteDataSource
import page.lifty.gdsclifty.core.network.model.response.DiaryResponse
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

class DefaultDiaryRepository @Inject constructor(
    diaryRemoteDataSource: DiaryRemoteDataSource,
    @Dispatcher(Default) private val defaultDispatcher: CoroutineDispatcher,
) : DiaryRepository {
    override val getDiary: Flow<DiaryResponse> = diaryRemoteDataSource.getDiary
        .map { it }
        .onEach { diaryResponse ->
            Timber.d("${diaryResponse.code} ${diaryResponse.message}")
        }
        .catch { exception ->
            Timber.d("${exception.message}")
        }
        .flowOn(defaultDispatcher)
}