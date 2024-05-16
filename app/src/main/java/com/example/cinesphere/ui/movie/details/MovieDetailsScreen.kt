package com.example.cinesphere.ui.movie.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.cinesphere.R
import com.example.cinesphere.data.model.Genre
import com.example.cinesphere.ui.navigation.NavigationDestination
import kotlin.text.Typography.bullet

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(uiState: MovieDetailsUiState) {

    when(uiState) {
        is MovieDetailsUiState.Success -> {
            val movie = uiState.movie

            Column {
                MovieDetailsHeader(
                    backdropUrl = movie.backdropUrl,
                    title = movie.title,
                    tagline = movie.tagline,
                    genres = movie.genres.slice(0..1)
                )

            }
        }
        else -> {
            Text("Error")
        }
    }
}

@Composable
fun MovieDetailsHeader(
    backdropUrl: String?,
    title: String,
    tagline: String?,
    genres: List<Genre>,
    modifier: Modifier = Modifier
) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(backdropUrl)
                .crossfade(true)
                .build(),
            contentDescription = "",
            modifier = Modifier.onGloballyPositioned {
                size = it.size
            }
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = size.height.toFloat() / 3,
                        endY = size.height.toFloat()
                    ),
                )
        )

        val genresText = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray,
                )
            ) {
                genres.forEachIndexed {index, genre ->
                    append(genre.name)

                    if (index != genres.size - 1)
                        append(bullet)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(bottom = 13.dp, start = 8.dp, end = 8.dp)
        ) {
            ProvideTextStyle(value = TextStyle(
                color = Color.LightGray,
                fontWeight = FontWeight.Bold
            )) {
                Column {
                    Text(text = title, fontSize = 22.sp)

                    Spacer(modifier = Modifier.height(10.dp))

                    ProvideTextStyle(value = TextStyle(
                        fontSize = 11.sp
                    )) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = genresText, modifier = Modifier)

                            Spacer(Modifier.width(140.dp))

                            if (tagline != null) {
                                Text(
                                    text = tagline,
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 2,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}