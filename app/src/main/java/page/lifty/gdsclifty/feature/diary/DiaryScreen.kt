package page.lifty.gdsclifty.feature.diary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import page.lifty.gdsclifty.core.network.model.response.DiaryData
import page.lifty.gdsclifty.feature.diary.DiaryContract.DiaryUiState

@Composable
internal fun DiaryRoute(
    modifier: Modifier = Modifier,
    onDiaryDetailClick: () -> Unit,
    diaryViewModel: DiaryViewModel = hiltViewModel(),
) {
    val diaryUiState by remember(diaryViewModel) { diaryViewModel.diaryUiState }.collectAsStateWithLifecycle()

    DiaryScreen(
        modifier = modifier,
        onDiaryDetailClick = onDiaryDetailClick,
        diaryUiState = diaryUiState
    )
}

@Composable
internal fun DiaryScreen(
    modifier: Modifier = Modifier,
    onDiaryDetailClick: () -> Unit,
    diaryUiState: DiaryUiState = DiaryUiState.Loading,
) {
    Column {
        Button(onClick = onDiaryDetailClick) {
            Text(text = "Navigate to diary detail")
        }
        when (diaryUiState) {
            DiaryUiState.Loading,
            -> Unit

            is DiaryUiState.Success -> {
                val diaries: List<DiaryData> = diaryUiState.diary.data
                LazyColumn {
                    items(
                        items = diaries
                    ) { diary ->
                        DiaryItem(diary = diary)
                    }
                }
            }
        }
    }
}

@Composable
fun DiaryItem(
    modifier: Modifier = Modifier,
    diary: DiaryData,
) {
    Card(
        onClick = {}
    ) {
        Column {
            Text(text = diary.date)
            Text(text = diary.content)
            Text(text = diary.keywords.toString())
        }
    }
}