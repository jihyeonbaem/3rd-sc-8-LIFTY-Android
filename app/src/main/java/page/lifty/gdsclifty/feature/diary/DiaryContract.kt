package page.lifty.gdsclifty.feature.diary

import page.lifty.gdsclifty.core.network.model.response.DiaryResponse

object DiaryContract {
    sealed interface DiaryUiState {
        data object Loading : DiaryUiState
        data class Success(
            val diary: DiaryResponse,
        ) : DiaryUiState
    }
}