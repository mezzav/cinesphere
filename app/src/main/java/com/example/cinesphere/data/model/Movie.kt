package com.example.cinesphere.data.model

data class Movie(
    val id: Int,
    val adult: Boolean,
    val backdropUrl: String?,
    val posterUrl: String?,
    val overview: String,
    val title: String,
)
