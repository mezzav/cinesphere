package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkProductionCountry(
    @SerialName("iso_3166_1")
    val shorthand: String,
    val name: String
)