package com.example.cinesphere.data.repository

import com.example.cinesphere.data.remote.service.TMDBService
import javax.inject.Inject

interface TMDBRepository {
}

class NetworkTMDBRepository @Inject constructor(
    private val service: TMDBService
) : TMDBRepository {

}