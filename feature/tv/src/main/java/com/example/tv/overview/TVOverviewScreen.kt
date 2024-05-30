package com.example.tv.overview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.model.TVShow
import com.example.ui.PagerItemList

@Composable
fun TVOverviewScreen(
    onTheAirPagingList: LazyPagingItems<TVShow>,
    topRatedPagingList: LazyPagingItems<TVShow>,
    popularPagingList: LazyPagingItems<TVShow>,
    navigateToDetails: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                start = 8.dp,
                top = 16.dp,
                end = 8.dp
            )
    ) {
        PagerItemList(
            title = "Top Rated",
            items = topRatedPagingList,
            navigateToDetails = navigateToDetails,
            modifier = Modifier.height(8.dp)
        )

        PagerItemList(
            title = "Popular",
            items = popularPagingList,
            navigateToDetails = navigateToDetails
        )

        PagerItemList(
            title = "On TV",
            items = onTheAirPagingList,
            navigateToDetails = navigateToDetails,
            modifier = Modifier.height(8.dp)
        )

    }
}