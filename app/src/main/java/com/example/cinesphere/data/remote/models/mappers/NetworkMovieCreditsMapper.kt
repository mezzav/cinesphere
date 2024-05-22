package com.example.cinesphere.data.remote.models.mappers

import com.example.network.model.NetworkMovieCredits
import com.example.utils.NetworkMapper
import com.example.domain.FormatTMDBUrlUseCase
import com.example.model.Cast
import com.example.model.Crew
import com.example.model.MovieCredits
import javax.inject.Inject


class NetworkMovieCreditsMapper @Inject constructor(
    private val formatTMDBUrlUseCase: FormatTMDBUrlUseCase
): NetworkMapper<NetworkMovieCredits, MovieCredits> {
    override fun mapFromNetwork(networkModel: NetworkMovieCredits): MovieCredits {
        return MovieCredits(
            id = networkModel.id,
            cast = networkModel.cast.map { networkCast ->
                Cast(
                    id = networkCast.id,
                    adult = networkCast.adult,
                    gender = networkCast.gender,
                    name = networkCast.name,
                    profileUrl = networkCast.profilePath?.let { formatTMDBUrlUseCase(185, it) },
                    role = networkCast.character
                )
            },
            crew = networkModel.crew.map { networkCrew ->
                Crew(
                    id = networkCrew.id,
                    adult = networkCrew.adult,
                    gender = networkCrew.gender,
                    name = networkCrew.name,
                    profileUrl = networkCrew.profilePath?.let { formatTMDBUrlUseCase(185, it) },
                    department = networkCrew.department,
                    role = networkCrew.job
                )
            }
        )
    }
}