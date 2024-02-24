package page.lifty.gdsclifty.feature.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import page.lifty.gdsclifty.feature.login.LoginRoute

const val LOGIN_GRAPH_ROUTE_PATTERN = "login_graph"
const val LOGIN_ROUTE = "login_route"

fun NavGraphBuilder.loginNavGraph(
    onHomeClick: () -> Unit,
) {
    navigation(
        route = LOGIN_GRAPH_ROUTE_PATTERN,
        startDestination = LOGIN_ROUTE,
    ) {
        loginScreen(onHomeClick = onHomeClick)
    }
}

private fun NavGraphBuilder.loginScreen(
    onHomeClick: () -> Unit
) {
    composable(route = LOGIN_ROUTE) {
        LoginRoute(onHomeClick = onHomeClick)
    }
}