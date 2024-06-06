package com.example.tv.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.example.model.TVShowDetails
import com.example.ui.ErrorIndicator
import com.example.ui.Header
import com.example.ui.LoadingIndicator
import com.example.ui.OverviewTab
import com.example.ui.TitleCard
import com.example.assets.R
import com.example.ui.LabelText

val tabs: List<String> = listOf("Details")

@Composable
fun TVShowDetailsScreenContainer(uiState: TVShowDetailsUiState) {
    when(uiState) {
        is TVShowDetailsUiState.Success -> {
            TVShowDetailsScreen(show = uiState.show)
        }
        is TVShowDetailsUiState.Loading -> {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }
        else -> {
            ErrorIndicator(
                text = "An error occurred displaying the information.",
                boxModifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TVShowDetailsScreen(
    show: TVShowDetails
) {
    var state by remember { mutableIntStateOf(0) }

    Column {
        Box {
            Header(
                backdropUrl = show.backdropUrl
            )

            TitleCard(
                title = show.name,
                genres = show.genres.take(2),
                tagline = show.tagline,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        start = 8.dp,
                        bottom = 20.dp,
                        end = 8.dp
                    )
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
            0 -> OverviewTab(
                overview = show.overview,
                title = show.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            ) {

                if (show.creators.isEmpty()) {
                    LabelText(label = "Creators", text = "Not Specified" )
                }
                else {
                    LabelText(label = "Creators", items = show.creators.map { it.name } )

                }

                LabelText(
                    label = "First Aired On",
                    text = show.firstAiredOn
                )

                LabelText(
                    label = "Number of Seasons",
                    text = "${show.numOfSeasons} (${show.numOfEpisodes} episodes)"
                )
            }
        }
    }
}

fun getBadgeColors(status: String): Pair<Int, Int> {
    return when(status) {
        "Ended" -> {
            Pair(
                R.color.successColor,
                R.color.emeraldColor
            )
        }
        "Returning Series", "Planned", "In Production", "Pilot" -> {
            Pair(
                R.color.babyBlueColor,
                R.color.black25PercentColor
            )
        }
        else -> {
            Pair(
                R.color.warningColor,
                R.color.almondColor
            )
        }
    }
}