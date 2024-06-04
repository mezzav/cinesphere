package com.example.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSpokenLanguage(
    @SerialName("english_name")
    val englishName: String,

    @SerialName("iso_639_1")
    val shorthand: String,
    val name: String
)
