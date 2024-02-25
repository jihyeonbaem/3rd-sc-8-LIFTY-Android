package page.lifty.gdsclifty.core.domain.usecase.chat

import page.lifty.gdsclifty.core.network.model.request.ChatRequest

interface PostChatUseCase {
    suspend operator fun invoke(chatRequest: ChatRequest): String
}