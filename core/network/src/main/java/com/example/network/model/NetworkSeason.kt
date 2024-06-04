package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSeason(
    @SerialName("air_date")
    val airDate: String,

    @SerialName("episode_count")
    val numOfEpisodes: Int,

    val id: Int,
    val name: String,
    val overview: String,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("season_number")
    val season: Int,

    @SerialName("vote_average")
    val voteAverage: Double,
)