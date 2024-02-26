package page.lifty.gdsclifty.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @param color 배경색
 * @param contentColor Surface 자식에게 제공하는 기본 콘텐츠 색상
 */
@Composable
fun LiftySurface(
    modifier: Modifier = Modifier,
    roundedCornerShape: Dp = 12.dp,
    color: Color = Color.White,
    border: BorderStroke? = null,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(roundedCornerShape),
        color = color,
        border = border,
    ) {
        Box(
            modifier = Modifier.paddingSurface()
        ) {
            content()
        }
    }
}

@Composable
private fun Modifier.paddingSurface(): Modifier =
    this then Modifier.padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 12.dp)

@Preview(showSystemUi = true)
@Composable
fun LiftySurfacePreview() {
    LiftySurface {
        Text(text = "hewefwqfewqefwqefwqefwqefwqefwqefwqefqwfwqefwqefwqefwqefqwefqwefwqefqweflll")
    }
}