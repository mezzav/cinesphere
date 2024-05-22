package com.example.cinesphere.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.cinesphere.R

object MovieOverviewDestination: NavigationDestination {
    override val route = "movie_overview"
    override val titleRes = R.string.movie_overview_title
    override val drawable = R.drawable.movie
}

object MovieDetailsDestination: NavigationDestination {
    override val route = "movie_details"
    override val titleRes = R.string.movie_details
    override val drawable = 0
    const val id = "id"
    val routeWithArgs = "$route/{$id}"
    val arguments = listOf(
        navArgument(id) { type = NavType.IntType }
    )
}