package com.example.cinesphere.ui.movie.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.cinesphere.R
import com.example.cinesphere.ui.navigation.NavigationDestination

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

@Composable
fun MovieDetailsScreen(uiState: MovieDetailsUiState) {
    Text(uiState.movie.title)
}