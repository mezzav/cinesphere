package com.example.network

import com.example.network.model.NetworkMovieCredits
import com.example.network.model.NetworkMovieDetails
import com.example.network.model.NetworkMovieList
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TMDBService {
    @GET("movie/upcoming")
    suspend fun upcomingMovies(
        @Query("page") page: Int
    ): NetworkMovieList

    @GET("movie/popular")
    suspend fun popularMovies(
        @Query("page") page: Int
    ): NetworkMovieList

    @GET("movie/now_playing")
    suspend fun nowPlayingMovies(
        @Query("page") page: Int
    ): NetworkMovieList

    @GET("movie/{id}")
    suspend fun movieDetails(
        @Path("id") id: Int
    ): ApiResponse<NetworkMovieDetails>

    @GET("movie/{id}/credits")
    suspend fun movieCredits(
        @Path("id") id: Int
    ): ApiResponse<NetworkMovieCredits>
}