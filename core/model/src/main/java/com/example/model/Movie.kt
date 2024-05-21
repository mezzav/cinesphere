package com.example.model

import com.example.model.interfaces.ExternalOverviewModel

data class Movie(
    override val id: Int,
    override val posterUrl: String?,
    val adult: Boolean,
    val backdropUrl: String?,
    val overview: String,
    val title: String,
) : ExternalOverviewModel
