package com.example.movie.details

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
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Cast
import com.example.model.Crew
import com.example.model.MovieDetails
import com.example.movie.R
import com.example.ui.Header
import com.example.ui.IconText
import com.example.ui.ProductionMemberList
import com.example.ui.TitleCard


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

    Column(
        modifier = modifier
    ) {
        Box {
            Header(
                backdropUrl = movie.backdropUrl
            )

            TitleCard(
                title = movie.title,
                genres = movie.genres.take(2).map { it.name },
                tagline = movie.tagline,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 8.dp, bottom = 20.dp, end = 8.dp)
            )
        }

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
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                )
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
            IconText(
                painter = painterResource(R.drawable.calendar),
                text = releaseDate
            )
            IconText(
                painter = painterResource(id = R.drawable.time),
                text = runtime
            )
            IconText(
                painter = painterResource(R.drawable.language),
                text = languages
            )
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