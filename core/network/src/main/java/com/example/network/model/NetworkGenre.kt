package com.example.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkGenres(
    val id: Int,
    val name: String
)