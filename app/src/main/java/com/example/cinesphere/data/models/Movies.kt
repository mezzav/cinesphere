package com.example.cinesphere.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieList(
    val dates: DateRange,
    val page: Int,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int,
    
    val results: List<Movie>
)
@Serializable
data class Movie(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdrop: String,

    @SerialName("genre_ids")
    val genreIDs: List<Int>,
    val id: Int,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Float,

    @SerialName("poster_path")
    val poster: String,

    @SerialName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Float,

    @SerialName("vote_count")
    val voteCount: Int,
)
@Serializable
data class DateRange(
    val maximum: String,
    val minimum: String
)

