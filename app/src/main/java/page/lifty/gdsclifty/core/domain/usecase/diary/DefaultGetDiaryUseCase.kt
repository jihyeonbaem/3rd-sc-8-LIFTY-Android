package page.lifty.gdsclifty.core.domain.usecase.diary

import kotlinx.coroutines.flow.Flow
import page.lifty.gdsclifty.core.domain.repository.DiaryRepository
import page.lifty.gdsclifty.core.network.model.response.DiaryResponse
import javax.inject.Inject

class DefaultGetDiaryUseCase @Inject constructor(
    private val diaryRepository: DiaryRepository,
) : GetDiaryUseCase {
    override operator fun invoke(): Flow<DiaryResponse> = diaryRepository.getDiary
}