package page.lifty.gdsclifty.core.domain.repository

import page.lifty.gdsclifty.core.network.model.request.ChatRequest

interface ChatRepository {
    suspend fun postChat(chatRequest: ChatRequest): String
}