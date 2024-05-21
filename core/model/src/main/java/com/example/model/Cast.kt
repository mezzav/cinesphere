package com.example.model

import com.example.model.interfaces.ProductionMember

data class Cast(
    override val id: Int,
    override val adult: Boolean,
    override val gender: Int,
    override val name: String,
    override val profileUrl: String?,
    override val role: String,
) : ProductionMember