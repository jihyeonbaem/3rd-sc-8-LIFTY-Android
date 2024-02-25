package page.lifty.gdsclifty.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LiftyButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    roundedCornerShape: Dp = 8.dp,
    containerColor: Color,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(
        start = 20.dp, end = 20.dp, top = 16.dp, bottom = 16.dp
    ),
    content: @Composable RowScope.() -> Unit
) =
    Button(
        onClick = onClick,
        modifier = modifier.semantics { contentDescription = "Button" },
        enabled = enabled,
        shape = RoundedCornerShape(roundedCornerShape),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
        ),
        border = border,
        contentPadding = contentPadding,
    ) {
        content()
    }