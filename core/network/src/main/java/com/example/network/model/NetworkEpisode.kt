package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkEpisode(
    @SerialName("air_date")
    val airedOn: String,

    @SerialName("episode_number")
    val episode: Int,

    @SerialName("episode_type")
    val type: String,

    val id: Int,
    val name: String,
    val overview: String?,

    @SerialName("production_code")
    val productionCode: String,
    val runtime: Int,

    @SerialName("season_number")
    val season: Int,

    @SerialName("show_id")
    val showID: Int,

    @SerialName("still_path")
    val stillPath: String?,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int
)