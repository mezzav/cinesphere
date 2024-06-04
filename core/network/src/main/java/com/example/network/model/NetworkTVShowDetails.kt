package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkTVShowDetails(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdrop: String?,

    @SerialName("created_by")
    val creators: List<NetworkCreator>,

    @SerialName("episode_run_time")
    val runTimes: List<Int>,

    @SerialName("first_air_date")
    val firstAiredOn: String,
    val genres: List<NetworkGenres>,
    val homepage: String?,
    val id: Int,

    @SerialName("in_production")
    val inProduction: Boolean,
    val languages: List<String>,

    @SerialName("last_air_date")
    val lastAiredOn: String,

    @SerialName("last_episode_to_air")
    val lastEpisode: NetworkEpisode,
    val name: String,
    val networks: List<NetworkChannelNetwork>,


    @SerialName("next_episode_to_air")
    val nextEpisode: NetworkEpisode?,

    @SerialName("number_of_episodes")
    val numOfEpisodes: Int,

    @SerialName("number_of_seasons")
    val numOfSeasons: Int,

    @SerialName("origin_country")
    val originCountry: List<String>,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_name")
    val originalName: String,

    val overview: String,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("production_companies")
    val productionCompanies: List<NetworkProductionCompany>,

    @SerialName("production_countries")
    val productionCountries: List<NetworkProductionCountry>,
    val seasons: List<NetworkSeason>,


    @SerialName("spoken_languages")
    val spokenLanguages: List<NetworkSpokenLanguage>,
    val status: String,
    val tagline: String?,
    val type: String,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class NetworkCreator(
    @SerialName("credit_id")
    val creditID: String,
    val gender: Int,
    val id: Int,
    val name: String,

    @SerialName("original_name")
    val originalName: String,

    @SerialName("profile_path")
    val profile: String?
)


@Serializable
data class NetworkChannelNetwork(
    val id: Int,

    @SerialName("logo_path")
    val logoPath: String?,
    val name: String,

    @SerialName("origin_country")
    val originCountry: String
)