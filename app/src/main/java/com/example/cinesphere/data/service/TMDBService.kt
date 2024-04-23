package com.example.cinesphere.data.service

import com.example.cinesphere.data.models.MovieList
import retrofit2.http.GET

interface TMDBService {
    @GET("movie/upcoming")
    suspend fun upcomingMovies(): MovieList
}