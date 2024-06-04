package com.example.tv.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.tv.R
import com.example.tv.overview.TVOverviewScreen
import com.example.tv.overview.TVOverviewViewModel
import com.example.utils.NavigationHomeDestination

object TVOverviewDestination: NavigationHomeDestination {
    override val route = "tv_overview"
    override val titleRes = R.string.tv_overview_title
    override val drawable = R.drawable.tv
}

fun NavGraphBuilder.tvOverviewScreen(
    onNavigateToTVShowDetails: (Int) -> Unit
) {
    composable(route = TVOverviewDestination.route) {
        val viewModel = hiltViewModel<TVOverviewViewModel>()

        val onTheAirPagingList = viewModel.onTheAirTVShows.collectAsLazyPagingItems()
        val topRatedPagingList = viewModel.topRatedTVShows.collectAsLazyPagingItems()
        val popularPagingList = viewModel.popularTVShows.collectAsLazyPagingItems()

        TVOverviewScreen(
            onTheAirPagingList = onTheAirPagingList,
            topRatedPagingList = topRatedPagingList,
            popularPagingList = popularPagingList,
            navigateToDetails = onNavigateToTVShowDetails
        )
    }
}

fun NavController.navigateToTVOverview() {
    this.navigate(TVOverviewDestination.route)
}