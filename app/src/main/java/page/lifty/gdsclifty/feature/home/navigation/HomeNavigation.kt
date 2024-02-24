package page.lifty.gdsclifty.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import page.lifty.gdsclifty.feature.home.HomeRoute

const val HOME_GRAPH_ROUTE_PATTERN = "home_graph"
const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions) =
    navigate(HOME_GRAPH_ROUTE_PATTERN, navOptions)

fun NavGraphBuilder.homeNavGraph() {
    /**
     * navigation의 route는 그냥 그래프의 이름이다.
     * startDestination은 시작점을 나타낸다.
     */
    navigation(
        route = HOME_GRAPH_ROUTE_PATTERN,
        startDestination = HOME_ROUTE,
    ) {
        homeScreen()
    }
}

private fun NavGraphBuilder.homeScreen() {
    composable(route = HOME_ROUTE) {
        HomeRoute()
    }
}