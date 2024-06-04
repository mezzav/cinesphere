package com.example.tv.mappers

import com.example.domain.FormatDateUseCase
import com.example.domain.FormatRuntimeUseCase
import com.example.domain.FormatTMDBUrlUseCase
import com.example.model.Creator
import com.example.model.Episode
import com.example.model.TVShow
import com.example.model.TVShowDetails
import com.example.network.model.NetworkTVShow
import com.example.network.model.NetworkTVShowDetails
import com.example.utils.NetworkMapper
import javax.inject.Inject


class NetworkTVShowDetailsMapper @Inject constructor(
    private val formatTMDBUrlUseCase: FormatTMDBUrlUseCase,
    private val formatDateUseCase: FormatDateUseCase,
    private val formatRuntimeUseCase: FormatRuntimeUseCase
): NetworkMapper<NetworkTVShowDetails, TVShowDetails> {
    override fun mapFromNetwork(networkModel: NetworkTVShowDetails): TVShowDetails {
        return TVShowDetails(
            adult = networkModel.adult,
            backdropUrl = networkModel.backdrop?.let { formatTMDBUrlUseCase(1280, it) },
            creators = networkModel.creators.map { networkCreator ->
                Creator(
                    creditID = networkCreator.creditID,
                    gender = when(networkCreator.gender) {
                        0 -> "Not Specified"
                        1 -> "Female"
                        2 -> "Male"
                        else -> "Non-binary"
                    },
                    id = networkCreator.id,
                    name = networkCreator.name,
                    profileUrl = networkCreator.profile?.let { formatTMDBUrlUseCase(180, it) }
                )
            },
            firstAiredOn = formatDateUseCase(
                networkModel.firstAiredOn,
                "yyy-MM-dd",
                "MM/dd/yyyy"
            ),
            genres = networkModel.genres.map { it.name },
            homepage = networkModel.homepage,
            id = networkModel.id,
            inProduction = networkModel.inProduction,
            languages = networkModel.languages,
            lastAiredOn = formatDateUseCase(
                networkModel.lastAiredOn,
                "yyy-MM-dd",
                "MM/dd/yyyy"
            ),
            lastEpisode = Episode(
                airedOn = formatDateUseCase(
                    networkModel.lastEpisode.airedOn,
                    "yyy-MM-dd",
                    "MM/dd/yyyy"
                ),
                episode = networkModel.lastEpisode.episode,
                type = networkModel.lastEpisode.type,
                id = networkModel.lastEpisode.id,
                name = networkModel.lastEpisode.name,
                overview = networkModel.lastEpisode.overview,
                runtime = formatRuntimeUseCase(networkModel.lastEpisode.runtime),
                season = networkModel.lastEpisode.season,
                showID = networkModel.lastEpisode.showID,
                stillUrl = networkModel.lastEpisode.stillPath?.let { formatTMDBUrlUseCase(185, it) },
            ),
            name = networkModel.name,
            nextEpisode = networkModel.nextEpisode?.let { episode ->
                Episode(
                    airedOn = formatDateUseCase(
                        episode.airedOn,
                        "yyy-MM-dd",
                        "MM/dd/yyyy"
                    ),
                    episode = episode.episode,
                    type = episode.type,
                    id = episode.id,
                    name = episode.name,
                    overview = episode.overview,
                    runtime = formatRuntimeUseCase(episode.runtime),
                    season = episode.season,
                    showID = episode.showID,
                    stillUrl = episode.stillPath?.let { formatTMDBUrlUseCase(185, it) },
                )
            },
            numOfEpisodes = networkModel.numOfEpisodes,
            numOfSeasons = networkModel.numOfSeasons,
            overview = networkModel.overview,
            posterUrl = networkModel.posterPath?.let {
                formatTMDBUrlUseCase(342, it)
            },
            status = networkModel.status,
            tagline = networkModel.tagline,
            type = networkModel.type
        )
    }
}