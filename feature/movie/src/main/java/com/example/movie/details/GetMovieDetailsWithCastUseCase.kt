package com.example.movie.details

import com.example.model.MovieDetailsWithCredits
import com.example.movie.mappers.NetworkMovieCreditsMapper
import com.example.movie.mappers.NetworkMovieDetailsMapper
import com.example.movie.repository.NetworkTMDBRepository
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