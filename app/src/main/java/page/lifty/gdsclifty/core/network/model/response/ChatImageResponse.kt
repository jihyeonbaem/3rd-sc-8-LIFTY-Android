package page.lifty.gdsclifty.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ChatImageResponse(
    val code: Int,
    val message: String,
    val chatImageData: ChatImageData
)

@Serializable
data class ChatImageData(
    val id: Int,
    val imageUri: String,
)
