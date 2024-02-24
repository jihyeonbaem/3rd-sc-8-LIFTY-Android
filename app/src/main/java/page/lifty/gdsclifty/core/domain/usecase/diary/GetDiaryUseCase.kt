package page.lifty.gdsclifty.core.domain.usecase.diary

import kotlinx.coroutines.flow.Flow
import page.lifty.gdsclifty.core.network.model.response.DiaryResponse

interface GetDiaryUseCase {
    operator fun invoke(): Flow<DiaryResponse>
}