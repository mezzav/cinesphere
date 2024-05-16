package com.example.cinesphere.data.remote.models.mappers

import com.example.cinesphere.data.model.Cast
import com.example.cinesphere.data.model.Crew
import com.example.cinesphere.data.model.MovieCredits
import com.example.cinesphere.data.remote.models.NetworkMovieCredits
import com.example.cinesphere.data.utils.NetworkMapper
import com.example.cinesphere.domain.FormatTMDBUrlUseCase
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
                    character = networkCast.character
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
                    job = networkCrew.job
                )
            }
        )
    }
}