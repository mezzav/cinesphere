package com.example.tv.mappers

import com.example.domain.FormatTMDBUrlUseCase
import com.example.model.Movie
import com.example.model.TVShow
import com.example.network.model.NetworkMovie
import com.example.network.model.NetworkTVShow
import com.example.utils.NetworkMapper
import javax.inject.Inject

class NetworkTVShowMapper @Inject constructor(
    private val formatTMDBUrlUseCase: FormatTMDBUrlUseCase,
): NetworkMapper<NetworkTVShow, TVShow> {
    override fun mapFromNetwork(networkModel: NetworkTVShow): TVShow {
        return TVShow(
            id = networkModel.id,
            adult = networkModel.adult,
            backdropUrl = networkModel.backdrop?.let { formatTMDBUrlUseCase(1280, it) },
            posterUrl = networkModel.poster?.let { formatTMDBUrlUseCase(342, it) },
            overview = networkModel.overview,
            name = networkModel.name
        )
    }
}