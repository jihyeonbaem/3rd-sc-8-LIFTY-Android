package page.lifty.gdsclifty.feature.record

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RecordRoute(
    onRecordDetailClick: () -> Unit
) {
    RecordScreen(
        onRecordDetailClick = onRecordDetailClick,
    )
}

@Composable
fun RecordScreen(
    onRecordDetailClick: () -> Unit,
) {
    Column {
        Button(onClick = onRecordDetailClick) {
            Text(text = "Navigate to record detail")
        }
        Text(text = "RecordScreen")
    }
}

@Composable
fun RecordItem(
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = {}
    ) {
        Column {
            Row() {
                Text(text = "오늘의 감정")
                Text(text = "소소한 행복")
            }
            Text(text = "divider")
            Text(text = "일기일기~~")
            Text(text = "divider")
            Text(text = "1월 21일 일요일")
        }
    }
}