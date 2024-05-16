package com.example.cinesphere.data.model

data class MovieDetailsWithCredits(
    val movie: MovieDetails,
    val cast: List<Cast>,
    val crew: List<Crew>
)
