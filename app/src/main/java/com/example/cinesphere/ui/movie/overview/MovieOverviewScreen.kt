package com.example.cinesphere.ui.movie.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cinesphere.R
import com.example.cinesphere.data.model.Movie
import com.example.cinesphere.ui.navigation.NavigationDestination

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
        modifier = Modifier.padding(start = 8.dp, top = 16.dp, end = 8.dp)
    ) {
        MovieTypeContainer(
            header = "Popular",
            movies = popularMoviesPagingList,
            navigateToDetails
        )

        MovieTypeContainer(
            header = "Upcoming",
            movies = upcomingMoviesPagingList,
            navigateToDetails
        )

        MovieTypeContainer(
            header = "Now Playing",
            movies = nowPlayingMoviesPagingList,
            navigateToDetails
        )
    }
}

@Composable
fun MovieTypeContainer(
    header: String,
    movies: LazyPagingItems<Movie>,
    navigateToDetails: (Int) -> Unit
) {
    Text(header, fontSize = 24.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.padding(top = 8.dp))
    
    MovieList(movies = movies, navigateToDetails)

    Spacer(modifier = Modifier.padding(top = 8.dp))

}

@Composable
fun MovieList(
    movies: LazyPagingItems<Movie>,
    navigateToDetails: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(count = movies.itemCount) { index ->
            val item = movies[index]

            if (item != null) {
                MovieCard(movie = item, navigateToDetails)
            }

        }
    }
}

@Composable
fun MovieCard(
    movie: Movie,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box{
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.posterUrl)
                .crossfade(true)
                .build(),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.clickable {
                navigateToDetails(movie.id)
            }.clip(RoundedCornerShape(8.dp))
        )

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}