package com.example.cinesphere.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cinesphere.ui.search.SearchDestination
import com.example.cinesphere.ui.search.SearchScreen
import com.example.movie.navigation.MovieOverviewDestination
import com.example.movie.navigation.movieDetailsScreen
import com.example.movie.navigation.movieOverviewScreen
import com.example.movie.navigation.navigateToMovieDetails
import com.example.tv.navigation.tvOverviewScreen

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
        movieOverviewScreen(
            onNavigateToMovieDetails = { id ->
                navController.navigateToMovieDetails(id)
            }
        )

        movieDetailsScreen()

        tvOverviewScreen()

        composable(route = SearchDestination.route) {
            SearchScreen()
        }
    }
}
