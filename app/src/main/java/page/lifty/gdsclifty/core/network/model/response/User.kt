package page.lifty.gdsclifty.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val code: Int,
    val message: String,
    val data: Data
)

@Serializable
data class Data(
    val name: String,
    val profileUri: String,
    val level: Int,
    val exp: Int,
)