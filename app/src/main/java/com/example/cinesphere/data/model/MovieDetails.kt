package com.example.cinesphere.data.model

data class MovieDetails(
    val backdropUrl: String = "",
    val posterUrl: String = "",
    val budget: Int = 0,
    val genres: List<Genre> = emptyList(),
    val homepage: String = "",
    val imdbID: String = "",
    val overview: String = "",
    val releaseDate: String = "",
    val revenue: Int = 0,
    val runtime: Int = 0,
    val status: String = "",
    val tagline: String = "",
    val title: String = "",
)

data class Genre(
    val id: Int,
    val name: String
)