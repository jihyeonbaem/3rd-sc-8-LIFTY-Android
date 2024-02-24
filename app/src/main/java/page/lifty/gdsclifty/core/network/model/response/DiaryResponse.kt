package page.lifty.gdsclifty.core.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class DiaryResponse(
    val code: Int,
    val message: String,
    val data: List<DiaryData>,
)

@Serializable
data class DiaryData(
    val date: String,
    val content: String,
    val keywords: List<String>,
)