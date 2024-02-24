package page.lifty.gdsclifty.app

import android.service.autofill.UserData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import page.lifty.gdsclifty.core.domain.usecase.DefaultSaveAccessTokenUseCase
import page.lifty.gdsclifty.core.domain.usecase.DefaultSaveRefreshTokenUseCase
import javax.inject.Inject

/**
 * MainActivity의 ViewModel에서 해야할 것...
 * splash 동안 UserDataRepository에서 유저 데이터를 받아온다.
 * onBoarding을 했는지
 * 다크모드 라이트모드 설정
 * 좋아요 목록
 * 북마크 목록 등 ...
 */
//@HiltViewModel
//class MainActivityViewModel @Inject constructor(
//    userDataRepository: UserDataRepository,
//) : ViewModel() {
//    val uiState: StateFlow<MainActivityUiState> = userDataRepository.userData.map {
//        Success(it)
//    }.stateIn(
//        scope = viewModelScope,
//        initialValue = Loading,
//        started = SharingStarted.WhileSubscribed(5_000),
//    )
//}
//
//sealed interface MainActivityUiState {
//    data object Loading : MainActivityUiState
//    data class Success(val userData: UserData) : MainActivityUiState
//}
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val defaultSaveAccessTokenUseCase: DefaultSaveAccessTokenUseCase,
    private val defaultSaveRefreshTokenUseCase: DefaultSaveRefreshTokenUseCase,
) : ViewModel(){
    fun saveAccessToken(accessToken: String) =
        viewModelScope.launch {
            defaultSaveAccessTokenUseCase.invoke(accessToken)
        }

    fun saveRefreshToken(refreshToken: String) =
        viewModelScope.launch {
            defaultSaveRefreshTokenUseCase.invoke(refreshToken)
        }
}