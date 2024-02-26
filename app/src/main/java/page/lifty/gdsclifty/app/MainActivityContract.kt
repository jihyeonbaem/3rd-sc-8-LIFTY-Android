package page.lifty.gdsclifty.app

import page.lifty.gdsclifty.app.model.UserData

object MainActivityContract {
    sealed interface MainActivityUiState {
        data object Loading : MainActivityUiState
        data class Success(val userData: UserData) : MainActivityUiState
    }
}