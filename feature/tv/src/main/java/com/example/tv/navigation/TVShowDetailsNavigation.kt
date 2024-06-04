package com.example.tv.navigation

import android.app.Activity
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tv.R
import com.example.tv.di.ViewModelFactoryProvider
import com.example.tv.ui.details.TVShowDetailsScreenContainer
import com.example.tv.ui.details.TVShowDetailsViewModel
import com.example.utils.NavigationDestination
import dagger.hilt.android.EntryPointAccessors

object TVShowDetailsDestination: NavigationDestination {
    override val route = "tv_details"
    override val titleRes = R.string.tv_show_details
    const val id = "id"
    val routeWithArgs = "$route/{$id}"
    val arguments = listOf(
        navArgument(id) { type = NavType.IntType }
    )
}

fun NavGraphBuilder.tvShowDetailsScreen() {
    composable(
        route = TVShowDetailsDestination.routeWithArgs,
        arguments = TVShowDetailsDestination.arguments
    ) {backStackEntry ->
        Log.d("TV_NAV_GRAPH", "Running TV SHOW Details NAV GRAPH")

        val movieID: Int? = backStackEntry.arguments?.getInt(TVShowDetailsDestination.id)

        val factory = EntryPointAccessors.fromActivity(
            LocalContext.current as Activity,
            ViewModelFactoryProvider::class.java
        ).tvShowDetailsFactory()

        val viewModel: TVShowDetailsViewModel = viewModel(
            factory = movieID?.let {
                TVShowDetailsViewModel.provideTVShowDetailsViewModelFactory(
                    factory,
                    it
                )
            }
        )

        val uiState = viewModel.uiState
        TVShowDetailsScreenContainer(uiState)
    }
}

fun NavController.navigateToTVShowDetails(id: Int) {
    this.navigate("${TVShowDetailsDestination.route}/$id")
}