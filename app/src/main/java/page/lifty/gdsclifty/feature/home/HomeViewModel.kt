package page.lifty.gdsclifty.feature.home

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
import page.lifty.gdsclifty.core.domain.usecase.userinfo.GetUserInfoUseCase
import page.lifty.gdsclifty.feature.home.HomeContract.UserInfoUiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUserInfoUseCase: GetUserInfoUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val userInfoUiState: StateFlow<UserInfoUiState> = getUserInfoUseCase()
        .map {
            UserInfoUiState.Success(
                userInfo = it
            )
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
            initialValue = UserInfoUiState.Loading
        )
}