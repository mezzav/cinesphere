package com.example.model

import com.example.model.interfaces.ExternalOverviewModel

data class TVShow(
    override val id: Int,
    override val posterUrl: String?,
    val adult: Boolean,
    val backdropUrl: String?,
    val overview: String,
    val name: String
): ExternalOverviewModel