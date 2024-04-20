package com.example.cinesphere.ui.movie.overview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.cinesphere.R
import com.example.cinesphere.ui.navigation.NavigationDestination

object MovieOverviewDestination: NavigationDestination {
    override val route = "movie_overview"
    override val titleRes = R.string.movie_overview_title
    override val drawable = R.drawable.movie
}

@Composable
fun MovieOverviewScreen() {
    Text("Hello from Movie Overview")
}