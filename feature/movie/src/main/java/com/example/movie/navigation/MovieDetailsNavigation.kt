package com.example.movie.navigation

import android.app.Activity
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movie.R
import com.example.movie.details.MovieDetailsScreenContainer
import com.example.movie.details.MovieDetailsViewModel
import com.example.movie.di.ViewModelFactoryProvider
import com.example.utils.NavigationDestination
import dagger.hilt.android.EntryPointAccessors

object MovieDetailsDestination: NavigationDestination {
    override val route = "movie_details"
    override val titleRes = R.string.movie_details_title
    const val id = "id"
    val routeWithArgs = "$route/{$id}"
    val arguments = listOf(
        navArgument(id) { type = NavType.IntType }
    )
}

fun NavGraphBuilder.movieDetailsScreen() {
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
}

fun NavController.navigateToMovieDetails(id: Int) {
    this.navigate("${MovieDetailsDestination.route}/$id")
}