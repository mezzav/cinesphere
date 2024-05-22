package com.example.movie.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.model.Movie
import com.example.ui.PagerItemList

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