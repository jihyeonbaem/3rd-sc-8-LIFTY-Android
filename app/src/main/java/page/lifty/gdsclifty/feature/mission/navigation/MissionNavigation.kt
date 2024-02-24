package page.lifty.gdsclifty.feature.mission.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import page.lifty.gdsclifty.feature.mission.MissionRoute

private const val MISSION_GRAPH_ROUTE_PATTERN = "mission_graph"
const val MISSION_ROUTE = "mission_route"

fun NavController.navigateToMission(navOptions: NavOptions) =
    navigate(MISSION_GRAPH_ROUTE_PATTERN, navOptions)

fun NavGraphBuilder.missionNavGraph() {
    navigation(
        route = MISSION_GRAPH_ROUTE_PATTERN,
        startDestination = MISSION_ROUTE,
    ) {
        missionScreen()
    }
}

private fun NavGraphBuilder.missionScreen() {
    composable(route = MISSION_ROUTE) {
        MissionRoute()
    }
}