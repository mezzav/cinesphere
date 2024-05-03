package com.example.cinesphere.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.cinesphere.ui.movie.overview.MovieOverviewDestination
import com.example.cinesphere.ui.movie.overview.MovieOverviewScreen
import com.example.cinesphere.ui.movie.overview.MovieOverviewViewModel
import com.example.cinesphere.ui.search.SearchDestination
import com.example.cinesphere.ui.search.SearchScreen
import com.example.cinesphere.ui.tv.overview.TVOverviewDestination
import com.example.cinesphere.ui.tv.overview.TVOverviewScreen

@Composable
fun CineSphereNavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = MovieOverviewDestination.route,
        modifier = modifier
    ) {
        composable(route = MovieOverviewDestination.route) {
            val viewModel = hiltViewModel<MovieOverviewViewModel>()

            val upcomingMoviesPagingList = viewModel.upcomingMovies.collectAsLazyPagingItems()
            val popularMoviesPagingList = viewModel.popularMovies.collectAsLazyPagingItems()
            val nowPlayingMoviesPagingList = viewModel.nowPlayingMovies.collectAsLazyPagingItems()

            MovieOverviewScreen(
                upcomingMoviesPagingList,
                popularMoviesPagingList,
                nowPlayingMoviesPagingList
            )
        }

        composable(route = TVOverviewDestination.route) {
            TVOverviewScreen()
        }

        composable(route = SearchDestination.route) {
            SearchScreen()
        }
    }
}
