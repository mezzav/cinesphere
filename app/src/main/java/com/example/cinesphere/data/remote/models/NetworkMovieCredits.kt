package com.example.cinesphere.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkMovieCredits(
    val id: Int,
    val cast: List<NetworkCast>,
    val crew: List<NetworkCrew>
)

@Serializable
data class NetworkCast(
    val adult: Boolean,
    val gender: Int,
    val id: Int,

    @SerialName("known_for_department")
    val knownForDepartment: String,

    val name: String,

    @SerialName("original_name")
    val originalName: String,
    val popularity: Float,

    @SerialName("profile_path")
    val profilePath: String?,

    @SerialName("cast_id")
    val castID: Int,
    val character: String,

    @SerialName("credit_id")
    val creditID: String,
    val order: Int,
)

@Serializable
data class NetworkCrew(
    val adult: Boolean,
    val gender: Int,
    val id: Int,

    @SerialName("known_for_department")
    val knownForDepartment: String,

    val name: String,

    @SerialName("original_name")
    val originalName: String,
    val popularity: Float,

    @SerialName("profile_path")
    val profilePath: String?,

    @SerialName("credit_id")
    val creditID: String,
    val department: String,
    val job: String

)
