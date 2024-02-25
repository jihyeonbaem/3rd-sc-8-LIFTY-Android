package page.lifty.gdsclifty.feature.mission

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter

@Composable
internal fun MissionRoute(
    modifier: Modifier = Modifier,
) {
    MissionScreen(
        modifier = modifier
    )
}

@Composable
internal fun MissionScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Column {
            개발대기(
                modifier = Modifier.weight(1f),
                missionTextUrl = "https://storage.googleapis.com/lifty-bucket/missionText.png",
                missionImageUrl = "https://storage.googleapis.com/lifty-bucket/missionImage.png",
            )
        }
    }
}

@Composable
private fun 개발대기(
    modifier: Modifier = Modifier,
    missionTextUrl: String,
    missionImageUrl: String,
) {
    val missionTextPainter = rememberAsyncImagePainter(missionTextUrl)
    val missionImagePainter = rememberAsyncImagePainter(missionImageUrl)

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        Image(
            modifier = Modifier.matchParentSize(),
            painter = missionImagePainter,
            contentDescription = "missionImage"
        )
        Image(
            modifier = Modifier.matchParentSize(),
            painter = missionTextPainter,
            contentDescription = "missionImage"
        )
    }
}