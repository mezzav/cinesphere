package com.example.model

data class Episode(
    val airedOn: String,
    val episode: Int,
    val type: String,
    val id: Int,
    val name: String,
    val overview: String?,
    val runtime: String,
    val season: Int,
    val showID: Int,
    val stillUrl: String?
)
