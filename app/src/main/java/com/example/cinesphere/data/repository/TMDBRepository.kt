package com.example.cinesphere.data.repository

import com.example.cinesphere.data.remote.models.NetworkMovieList
import com.example.cinesphere.data.remote.service.TMDBService
import javax.inject.Inject

interface TMDBRepository {
    suspend fun upcomingMovies(): NetworkMovieList
}

class NetworkTMDBRepository @Inject constructor(
    private val service: TMDBService
) : TMDBRepository {
    override suspend fun upcomingMovies(): NetworkMovieList = service.upcomingMovies()
}