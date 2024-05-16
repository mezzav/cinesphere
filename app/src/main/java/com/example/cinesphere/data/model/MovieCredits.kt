package com.example.cinesphere.data.model

data class MovieCredits(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)

data class Cast(
    val id: Int,
    val adult: Boolean,
    val gender: Int,
    val name: String,
    val profileUrl: String?,
    val character: String,
)

data class Crew(
    val id: Int,
    val adult: Boolean,
    val gender: Int,
    val name: String,
    val profileUrl: String?,
    val department: String,
    val job: String,
)