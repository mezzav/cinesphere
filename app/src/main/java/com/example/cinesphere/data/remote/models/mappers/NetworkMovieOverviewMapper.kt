package com.example.cinesphere.data.remote.models.mappers

import com.example.cinesphere.data.model.Movie
import com.example.cinesphere.data.remote.models.NetworkMovie
import com.example.cinesphere.data.utils.NetworkMapper
import com.example.cinesphere.domain.FormatTMDBUrlUseCase
import javax.inject.Inject

class NetworkMovieMapper @Inject constructor(
    private val formatTMDBUrlUseCase: FormatTMDBUrlUseCase,
): NetworkMapper<NetworkMovie, Movie> {
    override fun mapFromNetwork(networkModel: NetworkMovie): Movie {
        return Movie(
            id = networkModel.id,
            adult = networkModel.adult,
            backdropUrl = networkModel.backdrop?.let { formatTMDBUrlUseCase(1280, it) },
            posterUrl = networkModel.poster?.let { formatTMDBUrlUseCase(342, it) },
            overview = networkModel.overview,
            title = networkModel.title
        )
    }
}