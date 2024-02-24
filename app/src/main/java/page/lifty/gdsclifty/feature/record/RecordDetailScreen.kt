package page.lifty.gdsclifty.feature.record

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun RecordDetailRoute(
    onBackClick: () -> Unit,
) {
    RecordDetailScreen(
        onBackClick = onBackClick,
    )
}

@Composable
fun RecordDetailScreen(
    onBackClick: () -> Unit,
) {
    Column {
        Button(onClick = onBackClick) {
            Text(text = "back")
        }
        Text(text = "RecordDetail")
    }
}