package page.lifty.gdsclifty.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import page.lifty.gdsclifty.core.network.model.response.UserInfoData
import page.lifty.gdsclifty.core.network.model.response.UserInfoResponse

@Composable
fun HomeRoute(

) {
    HomeScreen(

    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val userUiState by remember(viewModel) { viewModel.userUiState }
        .collectAsStateWithLifecycle(
            initialValue = UserInfoResponse(-1, "", UserInfoData("", "", -1, -1))
        )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Column {
            Spacer(modifier = Modifier.size(20.dp))
            레벨(
                profileImageUrl = userUiState.data.profileUri,
                현재레벨 = userUiState.data.level,
                현재경험치 = userUiState.data.exp
            )
            Spacer(modifier = Modifier.size(20.dp))
            캐릭터(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.size(20.dp))
            대화()
            Spacer(modifier = Modifier.size(40.dp))
        }
    }
}

@Composable
fun 캐릭터(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        Icon(
            modifier = Modifier.matchParentSize(),
            imageVector = Icons.Default.Favorite,
            contentDescription = ""
        )
    }
}

/**
 * 자신의 내용만큼 최소한의 높이를 차지
 * .height(intrinsicSize = IntrinsicSize.Min)
 * 부모 컨테이너의 제약 내에서 최대한의 높이를 차지
 * .height(intrinsicSize = IntrinsicSize.Min)
 */
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
            Row {
                Image(
                    painter = painter,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(12.dp))
                Column {
                    Text(text = "Level $현재레벨")
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = "$현재경험치 / 100")
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
    modifier: Modifier = Modifier
) {
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
                .paddingTextField()
        ) {
            Text(text = "같이 놀자!", modifier = Modifier.align(Alignment.CenterVertically))
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Favorite,
                contentDescription = "음성채팅"
            )
            Spacer(modifier = Modifier.size(8.dp))
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Favorite,
                contentDescription = "사진보내기"
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

@Composable
private fun Modifier.roundedCorner(percent: Int): Modifier = this then Modifier.clip(
    RoundedCornerShape(
        topStartPercent = percent,
        topEndPercent = percent,
        bottomStartPercent = percent,
        bottomEndPercent = percent,
    )
)

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {

}