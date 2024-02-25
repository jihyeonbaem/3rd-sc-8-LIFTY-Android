package page.lifty.gdsclifty.core.designsystem.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp

@Composable
fun LiftySpacer(
    size: Dp,
    modifier: Modifier = Modifier
) =
    Spacer(
        modifier = modifier
            .size(size)
            .testTag("Spacer")
    )