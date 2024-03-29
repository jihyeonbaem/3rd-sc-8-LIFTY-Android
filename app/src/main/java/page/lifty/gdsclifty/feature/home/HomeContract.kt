package page.lifty.gdsclifty.feature.home

import page.lifty.gdsclifty.core.network.model.response.UserInfoResponse

object HomeContract {
    sealed interface UserInfoUiState {
        data object Loading : UserInfoUiState
        data class Success(
            val userInfo: UserInfoResponse,
        ) : UserInfoUiState
    }

    sealed interface ChatUiState {
        data object Loading : ChatUiState
        data class Success(
            val chat: String,
        ) : ChatUiState
    }
}