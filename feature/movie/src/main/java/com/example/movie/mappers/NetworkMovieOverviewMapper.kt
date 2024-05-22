package com.example.movie.mappers

import com.example.model.Movie
import com.example.network.model.NetworkMovie
import com.example.utils.NetworkMapper
import com.example.domain.FormatTMDBUrlUseCase
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