package com.example.tv.repository

import com.example.network.TMDBService
import com.example.network.model.NetworkTVShowDetails
import com.example.utils.di.IoDispatcher
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface TMDBTVRepository {
    suspend fun fetchTVDetails(id: Int): ApiResponse<NetworkTVShowDetails>
}

class NetworkTMDBTVRepository @Inject constructor(
    private val service: TMDBService,

    @IoDispatcher
    private val dispatcher: CoroutineDispatcher
) : TMDBTVRepository {
    override suspend fun fetchTVDetails(id: Int): ApiResponse<NetworkTVShowDetails> = withContext(dispatcher) {
        service.tvDetails(id)
    }
}