package com.example.cinesphere.data.model

import com.example.ui.ExternalOverviewModel

data class Movie(
    override val id: Int,
    override val posterUrl: String?,
    val adult: Boolean,
    val backdropUrl: String?,
    val overview: String,
    val title: String,
) : ExternalOverviewModel
