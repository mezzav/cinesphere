package com.example.movie.mappers

import com.example.utils.NetworkMapper
import com.example.domain.FormatDateUseCase
import com.example.domain.FormatRuntimeUseCase
import com.example.domain.FormatTMDBUrlUseCase
import com.example.model.Genre
import com.example.model.MovieDetails
import com.example.network.model.NetworkMovieDetails
import javax.inject.Inject

class NetworkMovieDetailsMapper @Inject constructor(
    private val formatTMDBUrlUseCase: FormatTMDBUrlUseCase,
    private val formatRuntimeUseCase: FormatRuntimeUseCase,
    private val formatDateUseCase: FormatDateUseCase
): NetworkMapper<NetworkMovieDetails, MovieDetails> {
    override fun mapFromNetwork(networkModel: NetworkMovieDetails): MovieDetails {
        return MovieDetails(
            backdropUrl = networkModel.backdropPath?.let { formatTMDBUrlUseCase(1280, it) },
            posterUrl = networkModel.posterPath?.let { formatTMDBUrlUseCase(342, it) },
            budget = networkModel.budget,
            genres = networkModel.genres.map{ genre ->
                Genre(
                    id = genre.id,
                    name = genre.name
                )
            },
            homepage = networkModel.homepage,
            imdbID = networkModel.imdbID,
            overview = networkModel.overview,
            releaseDate = formatDateUseCase(
                networkModel.releaseDate,
                "yyy-MM-dd",
                "MM/dd/yyyy"
            ),
            revenue = networkModel.revenue,
            runtime = formatRuntimeUseCase(networkModel.runtime),
            status = networkModel.status,
            tagline = networkModel.tagline,
            title = networkModel.title,
            spokenLanguages = networkModel.spokenLanguages.map { it.englishName }
        )
    }
}