package com.example.movie.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movie.R
import com.example.movie.overview.MovieOverviewScreen
import com.example.movie.overview.MovieOverviewViewModel
import com.example.utils.NavigationHomeDestination

object MovieOverviewDestination: NavigationHomeDestination {
    override val route = "movie_overview"
    override val titleRes = R.string.movie_overview_title
    override val drawable = R.drawable.movie
}

fun NavGraphBuilder.movieOverviewScreen(
    onNavigateToMovieDetails: (Int) -> Unit
) {
    composable(route = "movie_overview") {
        val viewModel = hiltViewModel<MovieOverviewViewModel>()

        val upcomingMoviesPagingList = viewModel.upcomingMovies.collectAsLazyPagingItems()
        val popularMoviesPagingList = viewModel.popularMovies.collectAsLazyPagingItems()
        val nowPlayingMoviesPagingList = viewModel.nowPlayingMovies.collectAsLazyPagingItems()

        MovieOverviewScreen(
            upcomingMoviesPagingList,
            popularMoviesPagingList,
            nowPlayingMoviesPagingList,
            onNavigateToMovieDetails
        )
    }
}

fun NavController.navigateToMovieOverview() {
    this.navigate("movie_overview")
}