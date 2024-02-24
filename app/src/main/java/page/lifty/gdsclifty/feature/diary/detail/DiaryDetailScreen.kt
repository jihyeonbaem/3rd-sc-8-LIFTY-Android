package page.lifty.gdsclifty.feature.diary.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun DiaryDetailRoute(
    onBackClick: () -> Unit,
) {
    DiaryDetailScreen(
        onBackClick = onBackClick,
    )
}

@Composable
internal fun DiaryDetailScreen(
    onBackClick: () -> Unit,
) {
    Column {
        Button(onClick = onBackClick) {
            Text(text = "back")
        }
        Text(text = "DiaryDetail")
    }
}