package page.lifty.gdsclifty.app.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import page.lifty.gdsclifty.R

enum class TopLevelDestination(
    @StringRes val label: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
) {
    HOME(
        R.string.home,
        Icons.Filled.Home,
        Icons.Outlined.Home,
    ),
    MISSION(
        R.string.mission,
        Icons.Filled.Favorite,
        Icons.Outlined.FavoriteBorder,
    ),
    DIARY(
        R.string.diary,
        Icons.Filled.Create,
        Icons.Outlined.Create,
    ),
}