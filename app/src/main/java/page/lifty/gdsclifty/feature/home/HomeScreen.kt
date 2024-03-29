package page.lifty.gdsclifty.feature.home

import android.graphics.Paint.Align
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import page.lifty.gdsclifty.R
import page.lifty.gdsclifty.core.designsystem.component.LiftyIcon
import page.lifty.gdsclifty.core.designsystem.component.LiftySurface
import page.lifty.gdsclifty.core.designsystem.component.LiftyText
import page.lifty.gdsclifty.feature.home.HomeContract.UserInfoUiState
import page.lifty.gdsclifty.feature.home.HomeContract.ChatUiState

@Composable
internal fun HomeRoute(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val userInfoUiState by remember(homeViewModel) {
        homeViewModel.userInfoUiState
    }.collectAsStateWithLifecycle()

    val chatUiState by remember(homeViewModel) {
        homeViewModel.chatUiState
    }.collectAsStateWithLifecycle()

    val chatQuery by remember(homeViewModel) {
        homeViewModel.chatQuery
    }.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        onChatClick = homeViewModel::postChat,
        userInfoUiState = userInfoUiState,
        chatUiState = chatUiState,
        chatQuery = chatQuery,
        onChatQueryChange = homeViewModel::updateChatQuery,
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    chatQuery: String = "",
    onChatQueryChange: (String) -> Unit = {},
    onChatClick: () -> Unit = {},
    userInfoUiState: UserInfoUiState = UserInfoUiState.Loading,
    chatUiState: ChatUiState = ChatUiState.Loading,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.size(20.dp))
            when (userInfoUiState) {
                UserInfoUiState.Loading,
                -> Unit

                is UserInfoUiState.Success -> {
                    레벨(
                        profileImageUrl = "https://storage.googleapis.com/lifty-bucket/ThreeDee%20Male.png",
                        현재레벨 = userInfoUiState.userInfo.userInfoData.level,
                        현재경험치 = userInfoUiState.userInfo.userInfoData.exp,
                    )
                }
            }
            Spacer(modifier = Modifier.size(20.dp))
            when (chatUiState) {
                ChatUiState.Loading,
                -> Unit

                is ChatUiState.Success -> {
                    LiftySurface(
                        modifier = Modifier.padding(start = 30.dp, end = 30.dp),
                    ) {
                        Text(text = chatUiState.chat)
                    }
                }
            }

            Spacer(modifier = Modifier.size(20.dp))
            캐릭터(
                modifier = Modifier.weight(1f),
                chatBotImageUrl = "https://storage.googleapis.com/lifty-bucket/ThreeDee%20Male.png"
            )
            Spacer(modifier = Modifier.size(20.dp))



            대화(
                chatQuery = chatQuery,
                onChatQueryChange = onChatQueryChange,
                onChatClick = onChatClick
            )



            Spacer(modifier = Modifier.size(40.dp))
        }
    }

    // 시스템 UI(키보드, 시스템 네비게이션바 등)가 콘텐츠를 가릴 수 있는 영역
    Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
}



@Composable
fun 캐릭터(
    modifier: Modifier = Modifier,
    chatBotImageUrl: String,
) {
    val painter = rememberAsyncImagePainter(chatBotImageUrl)

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = painter,
            contentDescription = "chatBotImage"
        )
    }
}

@Composable
fun 레벨(
    modifier: Modifier = Modifier,
    profileImageUrl: String,
    현재레벨: Int,
    현재경험치: Int,
) {
    val painter = rememberAsyncImagePainter(profileImageUrl)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .height(intrinsicSize = IntrinsicSize.Min)
            .roundedCorner(dp = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .paddingLevel()
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape),
                    painter = painter,
                    contentDescription = "profileImage"
                )
                Spacer(modifier = Modifier.size(12.dp))
                Column {
                    LiftyText(text = "Level $현재레벨")
                    Spacer(modifier = Modifier.size(4.dp))
                    LiftyText(text = "$현재경험치 / 100")
                }
            }
            Spacer(modifier = Modifier.size(16.dp))
            LinearProgressIndicator(
                progress = { 현재경험치.toFloat() / 100 },
                color = Color(0xFFFF7CA2),
                trackColor = Color(0xFFD9D9D9),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(RoundedCornerShape(50.dp))
            )
        }
    }
}

@Composable
fun 대화(
    modifier: Modifier = Modifier,
    onChatClick: () -> Unit,
    chatQuery: String,
    onChatQueryChange: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp)
            .height(50.dp)
            .roundedCorner(dp = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .paddingTextField(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                modifier = Modifier.weight(1f),
                value = chatQuery,
                onValueChange = onChatQueryChange,
            )
            IconButton(
                onClick = {
                    onChatClick()
                    keyboardController?.hide()
                }
            ) {
                LiftyIcon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "send message"
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Image(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.mic_icon),
                contentDescription = "google_icon"
            )
            Spacer(modifier = Modifier.size(8.dp))
            Image(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.camera_icon),
                contentDescription = "google_icon"
            )

        }
    }
}


@Composable
private fun Modifier.paddingTextField(): Modifier =
    this then Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)

@Composable
private fun Modifier.paddingLevel(): Modifier =
    this then Modifier.padding(start = 24.dp, end = 24.dp, top = 12.dp, bottom = 12.dp)


@Composable
private fun Modifier.roundedCorner(dp: Dp): Modifier = this then Modifier.clip(
    RoundedCornerShape(
        topStart = dp,
        topEnd = dp,
        bottomStart = dp,
        bottomEnd = dp,
    )
)

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {

}