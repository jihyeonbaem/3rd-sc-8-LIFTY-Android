package page.lifty.gdsclifty.feature.mission

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
    Text(text = "Mission")
}