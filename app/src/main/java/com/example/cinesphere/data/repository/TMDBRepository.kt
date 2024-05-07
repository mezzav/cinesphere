package com.example.cinesphere.data.repository

import com.example.cinesphere.data.remote.models.NetworkMovieDetails
import com.example.cinesphere.data.remote.service.TMDBService
import javax.inject.Inject

interface TMDBRepository {
    suspend fun fetchMovieDetails(id: Int): NetworkMovieDetails
}

class NetworkTMDBRepository @Inject constructor(
    private val service: TMDBService
) : TMDBRepository {
    override suspend fun fetchMovieDetails(id: Int): NetworkMovieDetails = service.movieDetails(id)
}