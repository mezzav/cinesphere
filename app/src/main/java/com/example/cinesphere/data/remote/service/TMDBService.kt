package com.example.cinesphere.data.remote.service

import com.example.cinesphere.data.remote.models.NetworkMovieList
import retrofit2.http.GET

interface TMDBService {
    @GET("movie/upcoming")
    suspend fun upcomingMovies(): NetworkMovieList
}