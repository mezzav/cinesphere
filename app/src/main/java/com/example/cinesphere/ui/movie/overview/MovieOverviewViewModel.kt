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
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


sealed interface MovieOverviewUIState {
    data class Success(val data: List<Movie>) : MovieOverviewUIState
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
                val result = repository.upcomingMovies()

                val movies = result.results.map { movie ->
                    movie.copy(
                        backdrop = formatTMDBUrlUseCase(352, movie.backdrop),
                        poster = formatTMDBUrlUseCase(342, movie.poster)
                    )
                }.map { movie ->
                    movie.asExternalModel()
                }

                MovieOverviewUIState.Success(movies)
            } catch (e: IOException) {
                MovieOverviewUIState.Error(e.message)
            } catch (e: HttpException) {
                MovieOverviewUIState.Error(e.message)
            }
        }
    }
}