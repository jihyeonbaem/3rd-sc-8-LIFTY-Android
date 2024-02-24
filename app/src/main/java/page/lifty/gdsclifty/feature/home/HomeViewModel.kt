package page.lifty.gdsclifty.feature.home

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
import page.lifty.gdsclifty.core.domain.usecase.DefaultUserUseCase
import page.lifty.gdsclifty.core.network.model.response.UserInfoData
import page.lifty.gdsclifty.core.network.model.response.UserInfoResponse
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    defaultUserUseCase: DefaultUserUseCase,
) : ViewModel() {

    val userUiState: StateFlow<UserInfoResponse> = defaultUserUseCase.invoke()
        .map { it }
        .onCompletion { Timber.tag("JHB").d(it) }
        .onStart { }
        .catch {
            Timber.tag("JHB").d(it.message ?: "unKnown error")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UserInfoResponse(-1, "", UserInfoData("", "", -1, -1))
        )
}