package page.lifty.gdsclifty.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import page.lifty.gdsclifty.app.ui.MainAppState
import page.lifty.gdsclifty.feature.home.navigation.HOME_GRAPH_ROUTE_PATTERN
import page.lifty.gdsclifty.feature.home.navigation.homeNavGraph
import page.lifty.gdsclifty.feature.login.navigation.LOGIN_GRAPH_ROUTE_PATTERN
import page.lifty.gdsclifty.feature.login.navigation.loginNavGraph
import page.lifty.gdsclifty.feature.mission.navigation.missionNavGraph
import page.lifty.gdsclifty.feature.diary.navigation.navigateToDiaryDetail
import page.lifty.gdsclifty.feature.diary.navigation.diaryNavGraph

@Composable
fun MainNavHost(
    appState: MainAppState,
    startDestination: String,
) {
    val navController = appState.navController

    val navOptions = navOptions {
        popUpTo(LOGIN_GRAPH_ROUTE_PATTERN) {
            inclusive = true
        }
        launchSingleTop = true
        restoreState = true
    }
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        loginNavGraph(
            onHomeClick = {
                navController.navigate(HOME_GRAPH_ROUTE_PATTERN, navOptions)
            },
        )
        homeNavGraph()
        missionNavGraph()
        diaryNavGraph(
            onDiaryDetailClick = navController::navigateToDiaryDetail,
            onBackClick = navController::popBackStack,
        )
    }
}