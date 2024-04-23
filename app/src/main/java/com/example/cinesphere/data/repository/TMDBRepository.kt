package com.example.cinesphere.data.repository

import com.example.cinesphere.data.models.MovieList
import com.example.cinesphere.data.service.TMDBService
import javax.inject.Inject

interface TMDBRepository {
    suspend fun upcomingMovies(): MovieList
}

class NetworkTMDBRepository @Inject constructor(
    private val service: TMDBService
) : TMDBRepository {
    override suspend fun upcomingMovies(): MovieList = service.upcomingMovies()
}