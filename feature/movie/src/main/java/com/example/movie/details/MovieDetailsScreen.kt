package com.example.movie.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.model.Cast
import com.example.model.Crew
import com.example.model.MovieDetails
import com.example.movie.R
import com.example.ui.ProductionMemberList
import kotlin.text.Typography.bullet


val tabs: List<String> = listOf("Details", "Cast")

@Composable
fun MovieDetailsScreenContainer(uiState: MovieDetailsUiState) {
    when(uiState) {
        is MovieDetailsUiState.Success -> {
            Screen(
                movie = uiState.movie,
                cast = uiState.cast,
                crew = uiState.crew
            )
        }
        else -> {
            Text("Error")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    movie: MovieDetails,
    cast: List<Cast>,
    crew: List<Crew>,
    modifier: Modifier = Modifier
) {
    var state by remember { mutableIntStateOf(0) }

    val genres = if (movie.genres.size > 1) {
        movie.genres.slice(0..1).joinToString(separator = " ${bullet.toString()} ") { it.name }
    }
    else {
        movie.genres[0].name
    }

    Column(
        modifier = modifier
    ) {
        BackdropHeader(
            backdropUrl = movie.backdropUrl,
            title = movie.title,
            tagline = movie.tagline,
            genres = genres
        )

        PrimaryTabRow(selectedTabIndex = state) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    text = { Text(tab) }
                )
            }
        }

        when(state) {
            0 -> {
                OverviewContent(
                    releaseDate = movie.releaseDate,
                    runtime = movie.runtime,
                    languages = movie.spokenLanguages.joinToString(separator = ","),
                    overview = movie.overview,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                )
            }
            1 -> {
                ProductionMemberList(
                    productionMembers = cast,
                    modifier = Modifier.
                        size(70.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}

@Composable
fun BackdropHeader(
    backdropUrl: String?,
    title: String,
    tagline: String?,
    genres: String,
    modifier: Modifier = Modifier
) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = modifier
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(backdropUrl)
                .crossfade(true)
                .build(),
            contentDescription = "$title's backdrop header",
            modifier = Modifier.onGloballyPositioned {
                size = it.size
            }
        )

        BackdropHeaderDetails(
            title = title,
            genres = genres,
            tagline = tagline,
            modifier = modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = size.height.toFloat() / 4,
                        endY = size.height.toFloat()
                    ),
                )
        )
    }
}

@Composable
fun BackdropHeaderDetails(
    title: String,
    genres: String,
    tagline: String?,
    modifier: Modifier = Modifier
) {
//gradient shadow
    Box(
        modifier = modifier
    ) {
        ProvideTextStyle(
            value = TextStyle(
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp
            )
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 18.dp, start = 8.dp, end = 8.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 22.sp,
                )

                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = genres, modifier = Modifier.width(160.dp))

                    if (tagline != null) {
                        Text(
                            text = tagline,
                            textAlign = TextAlign.End,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OverviewContent(
    overview: String,
    releaseDate: String,
    runtime: String,
    languages: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.width(160.dp)
        ) {
            IconText(R.drawable.calendar, releaseDate)
            IconText(R.drawable.time, runtime)
            IconText(R.drawable.language, languages)
        }
    }

    Spacer(modifier = Modifier.height(50.dp))
    
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.overview_title),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
        
        Text(overview)
    }
}

@Composable
fun IconText(iconID: Int, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = iconID), contentDescription = null)

        Spacer(Modifier.width(4.dp))

        Text(text, maxLines = 2, overflow = TextOverflow.Ellipsis)
    }
}