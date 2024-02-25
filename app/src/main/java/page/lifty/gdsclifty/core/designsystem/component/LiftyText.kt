package page.lifty.gdsclifty.core.designsystem.component

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun LiftyText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle(
        fontSize = 16.sp,
        color = Color.Black,
        letterSpacing = 0.sp,
        lineHeight = 20.sp,
    ),
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
) = BasicText(
    text = text,
    modifier = modifier,
    style = style,
    overflow = overflow,
    softWrap = softWrap,
    maxLines = maxLines,
    minLines = minLines,
)