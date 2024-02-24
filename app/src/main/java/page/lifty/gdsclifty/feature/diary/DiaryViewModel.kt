package page.lifty.gdsclifty.feature.diary

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import page.lifty.gdsclifty.core.domain.usecase.diary.GetDiaryUseCase
import page.lifty.gdsclifty.feature.diary.DiaryContract.DiaryUiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    getDiaryUseCase: GetDiaryUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val diaryUiState: StateFlow<DiaryUiState> = getDiaryUseCase()
        .map {
            DiaryUiState.Success(diary = it)
        }
        .onStart {
            Timber.d("onStart")
        }
        .onCompletion {
            Timber.e("onCompletion: $it")
        }
        .catch {
            Timber.e(it.message ?: "unKnown error")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DiaryUiState.Loading
        )
}