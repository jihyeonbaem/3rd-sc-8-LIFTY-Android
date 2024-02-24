package page.lifty.gdsclifty.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    val code: Int,
    val message: String,
    val data: UserInfoData,
)

@Serializable
data class UserInfoData(
    val name: String,
    val profileUri: String,
    val level: Int,
    val exp: Int,
)