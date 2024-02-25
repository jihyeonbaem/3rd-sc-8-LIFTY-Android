package page.lifty.gdsclifty.feature.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import page.lifty.gdsclifty.core.domain.usecase.chat.PostChatUseCase
import page.lifty.gdsclifty.core.domain.usecase.userinfo.GetUserInfoUseCase
import page.lifty.gdsclifty.core.network.model.request.ChatRequest
import page.lifty.gdsclifty.feature.home.HomeContract.UserInfoUiState
import page.lifty.gdsclifty.feature.home.HomeContract.ChatUiState
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getUserInfoUseCase: GetUserInfoUseCase,
    private val postChatUseCase: PostChatUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val chatQuery = savedStateHandle.getStateFlow(key = CHAT_QUERY, initialValue = "")

    private val _chatUiState = MutableStateFlow<ChatUiState>(ChatUiState.Loading)
    val chatUiState: StateFlow<ChatUiState> = _chatUiState.asStateFlow()

    fun updateChatQuery(query: String) {
        savedStateHandle[CHAT_QUERY] = query
    }

    fun postChat() {
        viewModelScope.launch {
            _chatUiState.value =
                ChatUiState.Success(postChatUseCase(ChatRequest(chatQuery.value, false)))
        }
    }

    val userInfoUiState: StateFlow<UserInfoUiState> = getUserInfoUseCase()
        .map {
            UserInfoUiState.Success(
                userInfo = it
            )
        }
        .onStart {
            Timber.d("userInfoUiState onStart")
        }
        .onCompletion {
            Timber.e("userInfoUiState onCompletion: $it")
        }
        .catch {
            Timber.e(it.message ?: "userInfoUiState unKnown error")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UserInfoUiState.Loading
        )

}

private const val CHAT_QUERY = "chatQuery"

//fun postChat(chatRequest: ChatRequest) {
//    viewModelScope.launch {
//        _chatUiState.value = ChatUiState.Success(chat = postChatUseCase(chatRequest))
//    }
//}