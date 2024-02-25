package page.lifty.gdsclifty.core.network.datasource

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import page.lifty.gdsclifty.core.common.network.Dispatcher
import page.lifty.gdsclifty.core.common.network.MainDispatchers.IO
import page.lifty.gdsclifty.core.network.ktor.Ktor
import page.lifty.gdsclifty.core.network.model.request.ChatRequest
import javax.inject.Inject

interface ChatRemoteDataSource {
    suspend fun postChatFlow(chatRequest: ChatRequest): String
}

class DefaultChatRemoteDataSource @Inject constructor(
    private val ktor: Ktor,
) : ChatRemoteDataSource {
    override suspend fun postChatFlow(chatRequest: ChatRequest): String =
        ktor.postChat(chatRequest)
}