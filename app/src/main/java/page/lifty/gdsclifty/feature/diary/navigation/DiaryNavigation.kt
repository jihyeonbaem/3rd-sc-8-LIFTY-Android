package page.lifty.gdsclifty.feature.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import page.lifty.gdsclifty.feature.diary.detail.DiaryDetailRoute
import page.lifty.gdsclifty.feature.diary.DiaryRoute

private const val DIARY_GRAPH_ROUTE_PATTERN = "diary_graph"
const val DIARY_ROUTE = "diary_route"
const val DIARY_DETAIL_ROUTE = "diary_detail_route"

fun NavController.navigateToDiary(navOptions: NavOptions) =
    navigate(DIARY_GRAPH_ROUTE_PATTERN, navOptions)

fun NavController.navigateToDiaryDetail() =
    navigate(DIARY_DETAIL_ROUTE)

fun NavGraphBuilder.diaryNavGraph(
    onDiaryDetailClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    navigation(
        route = DIARY_GRAPH_ROUTE_PATTERN,
        startDestination = DIARY_ROUTE,
    ) {
        diaryScreen(
            onDiaryDetailClick = onDiaryDetailClick
        )
        diaryDetailScreen(
            onBackClick = onBackClick
        )
    }
}

private fun NavGraphBuilder.diaryScreen(
    onDiaryDetailClick: () -> Unit
) {
    composable(route = DIARY_ROUTE) {
        DiaryRoute(
            onDiaryDetailClick = onDiaryDetailClick
        )
    }
}

private fun NavGraphBuilder.diaryDetailScreen(
    onBackClick: () -> Unit
) {
    composable(route = DIARY_DETAIL_ROUTE) {
        DiaryDetailRoute(
            onBackClick = onBackClick
        )
    }
}