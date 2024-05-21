package com.example.cinesphere.domain

import com.example.model.MovieDetailsWithCredits
import com.example.cinesphere.data.remote.models.mappers.NetworkMovieCreditsMapper
import com.example.cinesphere.data.remote.models.mappers.NetworkMovieDetailsMapper
import com.example.cinesphere.data.repository.NetworkTMDBRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.mapSuccess
import com.skydoves.sandwich.then
import javax.inject.Inject

class GetMovieDetailsWithCreditsUseCase @Inject constructor(
    private val repository: NetworkTMDBRepository,
    private val detailsMapper: NetworkMovieDetailsMapper,
    private val creditsMapper: NetworkMovieCreditsMapper
) {
    suspend operator fun invoke(id: Int) : ApiResponse<MovieDetailsWithCredits> {
        return repository.fetchMovieDetails(id).then { networkMovie ->
            repository.fetchMovieCredits(id).mapSuccess {
                val movie = detailsMapper.mapFromNetwork(networkMovie)
                val credits = creditsMapper.mapFromNetwork(this)

                MovieDetailsWithCredits(
                    movie = movie,
                    cast = credits.cast,
                    crew = credits.crew
                )
            }
        }
    }
}