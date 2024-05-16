package com.example.cinesphere.ui.movie.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cinesphere.data.model.Cast
import com.example.cinesphere.data.model.Crew
import com.example.cinesphere.data.model.MovieDetails
import com.example.cinesphere.domain.GetMovieDetailsWithCreditsUseCase
import com.skydoves.sandwich.ApiResponse
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch


sealed interface MovieDetailsUiState {
    data class Success(
        val movie: MovieDetails,
        val cast: List<Cast>,
        val crew: List<Crew>
    ) : MovieDetailsUiState
    data object Error : MovieDetailsUiState
    data object Loading : MovieDetailsUiState
}

class MovieDetailsViewModel @AssistedInject constructor(
    private val getMovieDetailsWithCreditsUseCase: GetMovieDetailsWithCreditsUseCase,
    @Assisted
    private val id: Int
) : ViewModel() {

    var uiState: MovieDetailsUiState by mutableStateOf(MovieDetailsUiState.Loading)
        private set
    init {
        viewModelScope.launch() {
            val movieDetailsWithCredits = getMovieDetailsWithCreditsUseCase(id)

            uiState = when(movieDetailsWithCredits) {
                is ApiResponse.Success -> {
                    MovieDetailsUiState.Success(
                        movie = movieDetailsWithCredits.data.movie,
                        cast = movieDetailsWithCredits.data.cast,
                        crew = movieDetailsWithCredits.data.crew
                    )
                }
                else -> {
                    MovieDetailsUiState.Error
                }
            }
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