package page.lifty.gdsclifty.core.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import page.lifty.gdsclifty.core.common.network.Dispatcher
import page.lifty.gdsclifty.core.common.network.MainDispatchers.Default
import page.lifty.gdsclifty.core.domain.repository.ChatRepository
import page.lifty.gdsclifty.core.network.datasource.ChatRemoteDataSource
import page.lifty.gdsclifty.core.network.model.request.ChatRequest
import timber.log.Timber
import javax.inject.Inject

class DefaultChatRepository @Inject constructor(
    private val chatRemoteDataSource: ChatRemoteDataSource,
) : ChatRepository {
    override suspend fun postChat(chatRequest: ChatRequest): String =
        chatRemoteDataSource.postChatFlow(chatRequest)
}