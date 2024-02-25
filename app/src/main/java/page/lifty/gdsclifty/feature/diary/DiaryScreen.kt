package page.lifty.gdsclifty.feature.diary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import page.lifty.gdsclifty.core.designsystem.component.LiftyCard
import page.lifty.gdsclifty.core.designsystem.component.LiftyDivider
import page.lifty.gdsclifty.core.designsystem.component.LiftySpacer
import page.lifty.gdsclifty.core.designsystem.component.LiftyText
import page.lifty.gdsclifty.core.network.model.response.DiaryData
import page.lifty.gdsclifty.core.network.model.response.DiaryResponse
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
        diaryUiState = diaryUiState,
        isDiaryExpanded = false,
    )
}

@Composable
internal fun DiaryScreen(
    modifier: Modifier = Modifier,
    onDiaryDetailClick: () -> Unit,
    diaryUiState: DiaryUiState = DiaryUiState.Loading,
    isDiaryExpanded: Boolean = false,
) {
    Column(
        modifier = modifier.padding(top = 40.dp, start = 16.dp, end = 16.dp)
    ) {
        LiftyText(
            text = "오늘은 어떤일이 있었나요?",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        LiftySpacer(size = 20.dp)
        when (diaryUiState) {
            DiaryUiState.Loading,
            -> Unit

            is DiaryUiState.Success -> {
                val diaries: List<DiaryData> = diaryUiState.diary.data
                LazyColumn {
                    items(
                        items = diaries
                    ) { diary ->
                        DiaryItem(
                            onDiaryDetailClick = onDiaryDetailClick,
                            diary = diary,
                            isExpanded = isDiaryExpanded,
                        )
                        LiftySpacer(size = 16.dp)
                    }
                }
            }
        }
    }
}

@Composable
fun DiaryItem(
    modifier: Modifier = Modifier,
    onDiaryDetailClick: () -> Unit,
    diary: DiaryData,
    isExpanded: Boolean = false,
) {
    LiftyCard(
        onClick = onDiaryDetailClick,
        modifier = modifier,
    ) {
        Column {
            LazyRow(
                modifier = Modifier.paddingCard(),
            ) {
                item {
                    LiftyText(text = "오늘의 생각")
                }
                items(diary.keywords) { keyword ->
                    DiaryEmotion(emotion = keyword)
                    LiftySpacer(size = 8.dp)
                }
            }
            LiftySpacer(size = 8.dp)
            LiftyDivider()
            LiftySpacer(size = 16.dp)
            LiftyText(
                modifier = Modifier.paddingCard(),
                maxLines = if (isExpanded) Int.MAX_VALUE else 10,
                text = diary.content, style = TextStyle(fontSize = 12.sp)
            )
            LiftySpacer(size = 16.dp)
            LiftyDivider()
            LiftySpacer(size = 8.dp)
            LiftyText(
                modifier = Modifier.paddingCard(),

                text = diary.date,
                style = TextStyle(fontSize = 10.sp)
            )
        }
    }
}

@Composable
private fun Modifier.paddingCard(): Modifier =
    this then Modifier.padding(start = 16.dp, end = 16.dp)


@Composable
private fun DiaryEmotion(
    emotion: String,
) {
    Surface(
        modifier = Modifier,
        shape = RoundedCornerShape(4.dp),
        color = Color(0xFFE9E9E9),
    ) {
        LiftyText(
            text = emotion,
            style = TextStyle(fontSize = 10.sp),
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, start = 8.dp, end = 8.dp),
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun DiaryItemPreview() {
    val isDiaryExpanded = remember { mutableStateOf(false) }
    DiaryScreen(
        isDiaryExpanded = isDiaryExpanded.value,
        onDiaryDetailClick = {isDiaryExpanded.value = !isDiaryExpanded.value},
        diaryUiState = DiaryUiState.Success(
            diary = DiaryResponse(
                code = 0,
                message = "0",
                data = listOf(
                    DiaryData(
                        date = "2024년 2월 26일 월요일",
                        content = "오늘은 흐린 날씨였지만, 따뜻한 햇살이 가끔씩 내리쬐는 봄날 같은 날씨였다. 아침에 일어나 커피 한 잔을 마시며 창밖을 바라보니, 잎이 돋기 시작한 나뭇가지와 새들의 노랫소리가 들려왔다.\n" +
                                "\n" +
                                "오늘은 할 일이 많았지만, 왠지 마음이 편안했다. 아마도 봄날의 따뜻한 분위기 때문일 것 같다.\n" +
                                "\n" +
                                "오전에는 오랫동안 미루었던 책을 읽기 시작했다. 책 속 주인공의 이야기에 빠져들면서 시간 가는 줄 몰랐다. 점심에는 간단한 라면을 먹고, 오후에는 쌓여있는 빨래를 세탁했다. 빨래를 세탁하면서 옷 하나하나를 정리하며 지난 시간들을 떠올렸다.\n" +
                                "\n" +
                                "저녁에는 친구들과 만나 저녁 식사를 했다. 오랜만에 만난 친구들과 이야기를 나누며 즐거운 시간을 보냈다.\n" +
                                "\n" +
                                "집으로 돌아오는 길에는 밤하늘을 수놓은 별들을 보며 오늘 하루를 되돌아봤다. 오늘은 특별한 일은 없었지만, 평범하면서도 행복한 하루였다.\n" +
                                "\n" +
                                "이렇게 평범한 하루하루가 쌓여 나의 삶을 만들어가는구나. 앞으로도 오늘처럼 행복하고 의미있는 하루들을 보내고 싶다.",
                        keywords = listOf("편안함", "회고")
                    ),
                    DiaryData(
                        date = "2024년 2월 28일 수요일",
                        content = "오늘은 며칠 만에 맑은 하늘을 볼 수 있는 날이었다. 따스한 햇살이 내리쬐는 아침, 나는 일기장을 꺼내 오늘의 감정들을 적기 시작했다.\n" +
                                "\n" +
                                "오늘 아침, 일어나자마자 창밖으로 보이는 푸른 하늘과 햇살을 보고 기분이 좋아졌다. 며칠 동안 흐린 날씨가 계속되어서 답답했던 기분이 한순간에 날아가는 듯했다.\n" +
                                "\n" +
                                "어제 친구들과 함께 즐거운 시간을 보냈던 기억이 떠올랐다. 오랜만에 만난 친구들과 맛있는 음식을 먹고, 이야기를 나누면서 웃음꽃을 피웠다. 친구들의 따뜻한 마음에 감사하는 마음이 들었다.\n" +
                                "\n" +
                                "오늘 오전에 약속 시간을 늦추는 실수를 했다. 바쁜 일상 속에서 약속 시간을 깜빡 잊고 있었던 것이다. 약속을 기다려주셨던 분께 송구스러운 마음이 들었다.\n" +
                                "\n" +
                                "요즘 갑자기 날씨 변화가 심한 것 같아 걱정된다. 건강을 유지하기 위해 더욱 신경 써야겠다는 생각이 들었다.\n" +
                                "\n" +
                                "다가오는 봄을 생각하며 설렘이 느껴졌다. 따뜻한 봄날에는 꽃들이 피고, 새들이 노래하며 세상이 더욱 아름다워질 것이다.\n" +
                                "\n" +
                                "일기를 쓰면서 오늘 하루 동안 느꼈던 다양한 감정들을整理할 수 있었다. 나의 감정을 솔직하게 표현하고, 반성하며 성장하는 기회가 된 것 같다. 앞으로도 꾸준히 일기를 써서 나의 내면을 돌아보고 싶다.",
                        keywords = listOf("감사", "후회", "희망")
                    ),
                    DiaryData(
                        date = "2024년 2월 27일 화요일",
                        content = "오늘은 며칠 만에 맑은 하늘을 볼 수 있는 날이었다. 따스한 햇살이 내리쬐는 아침, 나는 일기장을 꺼내 오늘의 감정들을 적기 시작했다.\n" +
                                "\n" +
                                "오늘 아침, 일어나자마자 창밖으로 보이는 푸른 하늘과 햇살을 보고 기분이 좋아졌다. 며칠 동안 흐린 날씨가 계속되어서 답답했던 기분이 한순간에 날아가는 듯했다.\n" +
                                "\n" +
                                "어제 친구들과 함께 즐거운 시간을 보냈던 기억이 떠올랐다. 오랜만에 만난 친구들과 맛있는 음식을 먹고, 이야기를 나누면서 웃음꽃을 피웠다. 친구들의 따뜻한 마음에 감사하는 마음이 들었다.\n" +
                                "\n" +
                                "오늘 오전에 약속 시간을 늦추는 실수를 했다. 바쁜 일상 속에서 약속 시간을 깜빡 잊고 있었던 것이다. 약속을 기다려주셨던 분께 송구스러운 마음이 들었다.\n" +
                                "\n" +
                                "요즘 갑자기 날씨 변화가 심한 것 같아 걱정된다. 건강을 유지하기 위해 더욱 신경 써야겠다는 생각이 들었다.\n" +
                                "\n" +
                                "다가오는 봄을 생각하며 설렘이 느껴졌다. 따뜻한 봄날에는 꽃들이 피고, 새들이 노래하며 세상이 더욱 아름다워질 것이다.\n" +
                                "\n" +
                                "일기를 쓰면서 오늘 하루 동안 느꼈던 다양한 감정들을整理할 수 있었다. 나의 감정을 솔직하게 표현하고, 반성하며 성장하는 기회가 된 것 같다. 앞으로도 꾸준히 일기를 써서 나의 내면을 돌아보고 싶다.",
                        keywords = listOf("감사", "후회", "희망")
                    )
                )
            )

        )
    )
}