package page.lifty.gdsclifty.core.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class ChatRequest(
    val request: String,
    val isImage: Boolean,
)