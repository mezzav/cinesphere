package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovieDetails(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String?,

    @SerialName("belongs_to_collection")
    val belongsToCollection: NetworkCollection?,

    val budget: Int,
    val genres: List<NetworkGenres>,
    val homepage: String?,
    val id: Int,

    @SerialName("imdb_id")
    val imdbID: String,

    @SerialName("origin_country")
    val originCountry: List<String>,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,


    val overview: String,
    val popularity: Float,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("production_companies")
    val productionCompanies: List<NetworkProductionCompany>,

    @SerialName("production_countries")
    val productionCountries: List<NetworkProductionCountry>,

    @SerialName("release_date")
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,

    @SerialName("spoken_languages")
    val spokenLanguages: List<NetworkSpokenLanguage>,

    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Float,

    @SerialName("vote_count")
    val voteCount: Int
)

@Serializable
data class NetworkCollection(
    val id: Int,
    val name: String?,

    @SerialName("poster_path")
    val poster: String?,

    @SerialName("backdrop_path")
    val backdrop: String?
)
