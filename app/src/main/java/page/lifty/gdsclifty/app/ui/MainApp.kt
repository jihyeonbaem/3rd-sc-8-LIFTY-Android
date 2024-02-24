package page.lifty.gdsclifty.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import page.lifty.gdsclifty.app.navigation.MainNavHost
import page.lifty.gdsclifty.app.navigation.TopLevelDestination

@Composable
fun MainApp(
    appState: MainAppState = rememberMainAppState(),
    startDestination: String,
) {
    Scaffold(bottomBar = {
        // 바텀바 숨기고 싶을 때 처리하는 방법
        // if (appState.shouldshowBottomBar)
        MainBottomBar(
            destinations = appState.topLevelDestinations,
            onNavigateToDestination = appState::navigateToTopLevelDestination,
            currentDestination = appState.currentDestination,
        )
    }) { innerPadding ->
        // Drawer를 쓰고싶으면 Row를 최상위로 선언하고 if(appState.NavRail) 로 사용하면됨
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
        ) {
            MainNavHost(
                appState = appState,
                startDestination = startDestination,
            )
        }
    }
}

@Composable
private fun MainBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
) {
    NavigationBar(
        modifier = Modifier
            .background(Color(0xFFF5F5F5))
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        containerColor = Color.White,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            NavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    Icon(
                        if (selected) destination.selectedIcon else destination.unselectedIcon,
                        contentDescription = destination.label.toString()
                    )
                },
                label = { Text(stringResource(destination.label)) },
                alwaysShowLabel = true,
                colors = NavigationBarItemColors(
                    selectedIconColor = Color(0xFFFF7CA2),
                    selectedTextColor = Color(0xFFFF7CA2),
                    unselectedIconColor = Color.LightGray,
                    unselectedTextColor = Color.LightGray,
                    disabledIconColor = Color(0xFFB0B8C1),
                    disabledTextColor = Color(0xFFB0B8C1),
                    selectedIndicatorColor = Color.Unspecified,
                ),
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false