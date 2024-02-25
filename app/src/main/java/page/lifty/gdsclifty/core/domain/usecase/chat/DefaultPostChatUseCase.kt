package page.lifty.gdsclifty.core.domain.usecase.chat

import page.lifty.gdsclifty.core.domain.repository.ChatRepository
import page.lifty.gdsclifty.core.network.model.request.ChatRequest
import javax.inject.Inject

class DefaultPostChatUseCase @Inject constructor(
    private val postRepository: ChatRepository,
) : PostChatUseCase {
    override suspend operator fun invoke(chatRequest: ChatRequest): String =
        postRepository.postChat(chatRequest)
}