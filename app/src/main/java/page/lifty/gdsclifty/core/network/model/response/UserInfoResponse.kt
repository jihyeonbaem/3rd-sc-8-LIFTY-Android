package page.lifty.gdsclifty.core.network.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfoResponse(
    val code: Int,
    val message: String,
    @SerialName("data")
    val userInfoData: UserInfoData,
)

@Serializable
data class UserInfoData(
    val name: String,
    val profileUri: String,
    val level: Int,
    val exp: Int,
)