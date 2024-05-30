package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkTVList(
    val page: Int,
    val results: List<NetworkTVShow>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)

@Serializable
data class NetworkTVShow(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdrop: String?,

    @SerialName("genre_ids")
    val genreIDs: List<Int>,
    val id: Int,

    @SerialName("origin_country")
    val originCountries: List<String>,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_name")
    val originalName: String,

    val overview: String,
    val popularity: Float,

    @SerialName("poster_path")
    val poster: String?,

    @SerialName("first_air_date")
    val airedOn: String,

    val name: String,

    @SerialName("vote_average")
    val voteAverage: Float,

    @SerialName("vote_count")
    val voteCount: Int,
)