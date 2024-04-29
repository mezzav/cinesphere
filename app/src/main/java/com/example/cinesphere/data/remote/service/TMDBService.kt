package com.example.cinesphere.data.remote.service

import com.example.cinesphere.data.remote.models.NetworkMovieList
import retrofit2.http.GET

interface TMDBService {
    @GET("movie/upcoming")
    suspend fun upcomingMovies(): NetworkMovieList

    @GET("movie/popular")
    suspend fun popularMovies(): NetworkMovieList

    @GET("movie/now_playing")
    suspend fun nowPlayingMovies(): NetworkMovieList
}