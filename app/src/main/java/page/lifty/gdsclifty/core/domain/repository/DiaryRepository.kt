package page.lifty.gdsclifty.core.domain.repository

import kotlinx.coroutines.flow.Flow
import page.lifty.gdsclifty.core.network.model.response.DiaryResponse

interface DiaryRepository {
    val getDiary: Flow<DiaryResponse>
}