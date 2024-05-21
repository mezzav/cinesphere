package com.example.cinesphere.ui.movie.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.cinesphere.R
import com.example.model.Movie
import com.example.cinesphere.ui.navigation.NavigationDestination
import com.example.ui.PagerItemList

object MovieOverviewDestination: NavigationDestination {
    override val route = "movie_overview"
    override val titleRes = R.string.movie_overview_title
    override val drawable = R.drawable.movie
}

@Composable
fun MovieOverviewScreen(
    upcomingMoviesPagingList: LazyPagingItems<Movie>,
    popularMoviesPagingList: LazyPagingItems<Movie>,
    nowPlayingMoviesPagingList: LazyPagingItems<Movie>,
    navigateToDetails: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp, top = 16.dp, end = 8.dp)
    ) {
        PagerItemList(
            title = "Popular",
            items = popularMoviesPagingList,
            navigateToDetails = navigateToDetails,
            modifier = Modifier.height(8.dp)
        )

        PagerItemList(
            title = "Upcoming",
            items = upcomingMoviesPagingList,
            navigateToDetails = navigateToDetails,
            modifier = Modifier.height(8.dp)
        )

        PagerItemList(
            title = "Now Playing",
            items = nowPlayingMoviesPagingList,
            navigateToDetails = navigateToDetails,
            modifier = Modifier.height(8.dp)
        )
    }
}