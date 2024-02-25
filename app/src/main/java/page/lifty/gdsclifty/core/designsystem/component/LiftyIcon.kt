package page.lifty.gdsclifty.core.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LiftyIcon(
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    tint: Color = Color.Black,
) =
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier.size(size),
        tint = tint,
    )