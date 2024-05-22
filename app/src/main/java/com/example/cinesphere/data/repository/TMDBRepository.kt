package com.example.cinesphere.data.repository

import com.example.network.model.NetworkMovieCredits
import com.example.network.model.NetworkMovieDetails
import com.example.network.TMDBService
import com.example.cinesphere.di.IoDispatcher
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface TMDBRepository {
    suspend fun fetchMovieDetails(id: Int): ApiResponse<NetworkMovieDetails>

    suspend fun fetchMovieCredits(id: Int): ApiResponse<NetworkMovieCredits>
}

class NetworkTMDBRepository @Inject constructor(
    private val service: TMDBService,

    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : TMDBRepository {
    override suspend fun fetchMovieDetails(id: Int): ApiResponse<NetworkMovieDetails> = withContext(dispatcher) {
        service.movieDetails(id)
    }

    override suspend fun fetchMovieCredits(id: Int): ApiResponse<NetworkMovieCredits> = withContext(dispatcher) {
        service.movieCredits(id)
    }
}