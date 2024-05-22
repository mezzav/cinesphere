package com.example.cinesphere.ui.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.movie.di.ViewModelFactoryProvider
import com.example.movie.details.MovieDetailsScreenContainer
import com.example.movie.details.MovieDetailsViewModel
import com.example.movie.overview.MovieOverviewScreen
import com.example.movie.overview.MovieOverviewViewModel
import com.example.cinesphere.ui.search.SearchDestination
import com.example.cinesphere.ui.search.SearchScreen
import com.example.cinesphere.ui.tv.overview.TVOverviewDestination
import com.example.cinesphere.ui.tv.overview.TVOverviewScreen
import dagger.hilt.android.EntryPointAccessors

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
                nowPlayingMoviesPagingList,
                navigateToDetails = { id ->
                   navController.navigate("${MovieDetailsDestination.route}/$id")
                }
            )
        }

        composable(
            route = MovieDetailsDestination.routeWithArgs,
            arguments = MovieDetailsDestination.arguments
        ) {backStackEntry ->
            val movieID: Int? = backStackEntry.arguments?.getInt(MovieDetailsDestination.id)

            val factory = EntryPointAccessors.fromActivity(
                LocalContext.current as Activity,
                ViewModelFactoryProvider::class.java
            ).movieDetailsFactory()

            val viewModel: MovieDetailsViewModel = viewModel(
                factory = movieID?.let {
                    MovieDetailsViewModel.provideMovieDetailsViewModelFactory(
                        factory,
                        it
                    )
                }
            )

            val uiState = viewModel.uiState

            MovieDetailsScreenContainer(uiState)
        }

        composable(route = TVOverviewDestination.route) {
            TVOverviewScreen()
        }

        composable(route = SearchDestination.route) {
            SearchScreen()
        }
    }
}
