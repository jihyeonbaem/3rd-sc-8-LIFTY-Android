package page.lifty.gdsclifty.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import page.lifty.gdsclifty.app.navigation.TopLevelDestination
import page.lifty.gdsclifty.app.navigation.TopLevelDestination.HOME
import page.lifty.gdsclifty.app.navigation.TopLevelDestination.MISSION
import page.lifty.gdsclifty.app.navigation.TopLevelDestination.DIARY
import page.lifty.gdsclifty.feature.diary.navigation.DIARY_ROUTE
import page.lifty.gdsclifty.feature.home.navigation.HOME_ROUTE
import page.lifty.gdsclifty.feature.home.navigation.navigateToHome
import page.lifty.gdsclifty.feature.mission.navigation.MISSION_ROUTE
import page.lifty.gdsclifty.feature.mission.navigation.navigateToMission
import page.lifty.gdsclifty.feature.diary.navigation.navigateToDiary

@Composable
fun rememberMainAppState(
    navController: NavHostController = rememberNavController()
): MainAppState {
    return remember(
        navController,
    ) {
        MainAppState(
            navController,
        )
    }
}

@Stable
class MainAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            HOME_ROUTE -> HOME
            MISSION_ROUTE -> MISSION
            DIARY_ROUTE -> DIARY
            else -> null
        }

    // TopBar, BottomBar, NavRail에서 사용할 최상위 목적지이다.
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // 그래프의 시작 목적지로 pop up해
            // 사용자가 item을 선택할 때 back stack에 목적지가 많이 싸이는 것을 방지한다.
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // 동일한 item을 다시 선택했을 때 복사본을 여러개 만들지 않는다.
            launchSingleTop = true
            // 이전에 선택한 항목을 다시 선택할 때 상태를 복원해준다.
            restoreState = true
        }

        when (topLevelDestination) {
            HOME -> navController.navigateToHome(topLevelNavOptions)
            MISSION -> navController.navigateToMission(topLevelNavOptions)
            DIARY -> navController.navigateToDiary(topLevelNavOptions)
        }
    }
}