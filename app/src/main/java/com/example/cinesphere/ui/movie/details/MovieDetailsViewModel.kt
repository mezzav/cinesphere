package com.example.cinesphere.ui.movie.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cinesphere.data.model.MovieDetails
import com.example.cinesphere.data.remote.models.NetworkMovieDetails
import com.example.cinesphere.data.remote.models.asExternalModel
import com.example.cinesphere.data.repository.NetworkTMDBRepository
import com.example.cinesphere.domain.FormatTMDBUrlUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface MovieDetailsUiState {
    val movie: MovieDetails
}

private class MutableMovieDetailsUiState: MovieDetailsUiState {
    override var movie: MovieDetails by mutableStateOf(MovieDetails())
}


class MovieDetailsViewModel @AssistedInject constructor(
    private val repository: NetworkTMDBRepository,
    private val formatTMDBUrlUseCase: FormatTMDBUrlUseCase,
    @Assisted
    private val id: Int
) : ViewModel() {

    private val _uiState = MutableMovieDetailsUiState()
    val uiState: MovieDetailsUiState= _uiState

    init {
        viewModelScope.launch() {
            var response: NetworkMovieDetails

            withContext(Dispatchers.IO) {
                response = repository.fetchMovieDetails(id)

                response = response.copy(
                    backdropPath = response.backdropPath?.let { formatTMDBUrlUseCase(1280, it) },
                    posterPath = response.posterPath?.let { formatTMDBUrlUseCase(342, it) }
                )
            }

            _uiState.movie = response.asExternalModel()
        }
    }
    @AssistedFactory
    interface Factory {
        fun create(id: Int): MovieDetailsViewModel
    }

    companion object {
        fun provideMovieDetailsViewModelFactory(
            factory: Factory,
            movieID: Int
        ): ViewModelProvider.Factory {
            return object: ViewModelProvider.Factory {
                override fun <T: ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(movieID) as T
                }
            }
        }
    }
}