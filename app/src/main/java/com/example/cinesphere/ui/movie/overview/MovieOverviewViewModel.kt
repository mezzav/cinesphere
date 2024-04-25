package com.example.cinesphere.ui.movie.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinesphere.data.models.MovieList
import com.example.cinesphere.data.repository.NetworkTMDBRepository
import com.example.cinesphere.domain.FormatTMDBUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


sealed interface MovieOverviewUIState {
    data class Success(val data: MovieList) : MovieOverviewUIState
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
        viewModelScope.launch {
            uiState = MovieOverviewUIState.Loading
            uiState = try {
                val movies = repository.upcomingMovies()

                val updatedMovies = movies.results.map {movie ->
                    movie.copy(
                        backdrop = formatTMDBUrlUseCase(352, movie.backdrop),
                        poster = formatTMDBUrlUseCase(342, movie.poster)
                    )
                }





                MovieOverviewUIState.Success(movies.copy(results = updatedMovies))
            } catch (e: IOException) {
                MovieOverviewUIState.Error(e.message)
            } catch (e: HttpException) {
                MovieOverviewUIState.Error(e.message)
            }
        }
    }
}