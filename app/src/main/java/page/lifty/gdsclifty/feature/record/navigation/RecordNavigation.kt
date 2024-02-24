package page.lifty.gdsclifty.feature.record.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import page.lifty.gdsclifty.feature.record.RecordDetailRoute
import page.lifty.gdsclifty.feature.record.RecordRoute

private const val RECORD_GRAPH_ROUTE_PATTERN = "record_graph"
const val RECORD_ROUTE = "record_route"
const val RECORD_DETAIL_ROUTE = "record_detail_route"

fun NavController.navigateToRecord(navOptions: NavOptions) =
    navigate(RECORD_GRAPH_ROUTE_PATTERN, navOptions)

fun NavController.navigateToRecordDetail() =
    navigate(RECORD_DETAIL_ROUTE)

fun NavGraphBuilder.recordNavGraph(
    onRecordDetailClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    navigation(
        route = RECORD_GRAPH_ROUTE_PATTERN,
        startDestination = RECORD_ROUTE,
    ) {
        recordScreen(
            onRecordDetailClick = onRecordDetailClick
        )
        recordDetailScreen(
            onBackClick = onBackClick
        )
    }
}

private fun NavGraphBuilder.recordScreen(
    onRecordDetailClick: () -> Unit
) {
    composable(route = RECORD_ROUTE) {
        RecordRoute(
            onRecordDetailClick = onRecordDetailClick
        )
    }
}

private fun NavGraphBuilder.recordDetailScreen(
    onBackClick: () -> Unit
) {
    composable(route = RECORD_DETAIL_ROUTE) {
        RecordDetailRoute(
            onBackClick = onBackClick
        )
    }
}