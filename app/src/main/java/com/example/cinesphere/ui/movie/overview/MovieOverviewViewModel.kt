package com.example.cinesphere.ui.movie.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinesphere.data.model.Movie
import com.example.cinesphere.data.remote.models.asExternalModel
import com.example.cinesphere.data.repository.NetworkTMDBRepository
import com.example.cinesphere.domain.FormatTMDBUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


sealed interface MovieOverviewUIState {
    data class Success(
        val upcomingMovies: List<Movie>,
        val popularMovies: List<Movie>,
        val nowPlayingMovies: List<Movie>
    ) : MovieOverviewUIState
    data class Error(val message: String?) : MovieOverviewUIState
    data object Loading : MovieOverviewUIState
}


@HiltViewModel
class MovieOverviewViewModel @Inject constructor(
    private val repository: NetworkTMDBRepository,
    private val formatTMDBUrlUseCase: FormatTMDBUrlUseCase
) : ViewModel() {

    var uiState: MovieOverviewUIState by mutableStateOf(MovieOverviewUIState.Loading)
        private set

    init {
        uiState = MovieOverviewUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            uiState = try {
                val upcomingMovies = fetchUpcomingMovies().shuffled()
                val popularMovies = fetchPopularMovies().shuffled()
                val nowPlayingMovies = fetchNowPlayingMovies().shuffled()

                MovieOverviewUIState.Success(
                    upcomingMovies = upcomingMovies,
                    popularMovies = popularMovies,
                    nowPlayingMovies = nowPlayingMovies
                )
            } catch (e: IOException) {
                MovieOverviewUIState.Error(e.message)
            } catch (e: HttpException) {
                MovieOverviewUIState.Error(e.message)
            }
        }
    }

    private suspend fun fetchUpcomingMovies(): List<Movie>  {
        val movies = repository.upcomingMovies()

        return movies.results.map { movie ->
            movie.copy(
                backdrop = formatTMDBUrlUseCase(1280, movie.backdrop),
                poster = formatTMDBUrlUseCase(342, movie.poster)
            ).asExternalModel()
        }
    }

    private suspend fun fetchPopularMovies(): List<Movie>  {
        val movies = repository.popularMovies()

        return movies.results.map { movie ->
            movie.copy(
                backdrop = formatTMDBUrlUseCase(1280, movie.backdrop),
                poster = formatTMDBUrlUseCase(342, movie.poster)
            ).asExternalModel()
        }
    }

    private suspend fun fetchNowPlayingMovies(): List<Movie>  {
        val movies = repository.nowPlayingMovies()

        return movies.results.map { movie ->
            movie.copy(
                backdrop = formatTMDBUrlUseCase(1280, movie.backdrop),
                poster = formatTMDBUrlUseCase(342, movie.poster)
            ).asExternalModel()
        }
    }
}