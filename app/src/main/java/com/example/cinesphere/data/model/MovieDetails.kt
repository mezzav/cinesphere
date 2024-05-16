package com.example.cinesphere.data.model

data class MovieDetails(
    val backdropUrl: String?,
    val posterUrl: String?,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val imdbID: String,
    val overview: String,
    val releaseDate: String,
    val revenue: Int,
    val spokenLanguages: List<String>,
    val runtime: String,
    val status: String,
    val tagline: String?,
    val title: String,
)

data class Genre(
    val id: Int,
    val name: String
)