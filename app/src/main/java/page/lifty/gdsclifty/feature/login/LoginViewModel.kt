package page.lifty.gdsclifty.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import page.lifty.gdsclifty.core.domain.usecase.DefaultGetAccessTokenUseCase
import page.lifty.gdsclifty.core.domain.usecase.DefaultGetRefreshTokenUseCase
import page.lifty.gdsclifty.core.domain.usecase.DefaultSaveAccessTokenUseCase
import page.lifty.gdsclifty.core.domain.usecase.DefaultSaveRefreshTokenUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    defaultGetAccessTokenUseCase: DefaultGetAccessTokenUseCase,
    defaultGetRefreshTokenUseCase: DefaultGetRefreshTokenUseCase,
) : ViewModel() {

    val getAccessToken = defaultGetAccessTokenUseCase()
            .map { it }
            .onCompletion { }
            .onStart { }
            .catch { }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )

    val getRefreshToken =
        defaultGetRefreshTokenUseCase.invoke()
            .map { it }
            .onCompletion { }
            .onStart { }
            .catch { }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = null
            )
}