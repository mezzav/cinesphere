package com.example.model

data class TVShowDetails(
    val adult: Boolean,
    val backdropUrl: String?,
    val creators: List<Creator>,
    val firstAiredOn: String,
    val genres: List<String>,
    val homepage: String?,
    val id: Int,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAiredOn: String,
    val lastEpisode: Episode,
    val name: String,
    val nextEpisode: Episode?,
    val numOfEpisodes: Int,
    val numOfSeasons: Int,
    val overview: String,
    val posterUrl: String?,
    val status: String,
    val tagline: String?,
    val type: String,
)
