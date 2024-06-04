package com.example.tv.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.model.TVShowDetails
import com.example.tv.mappers.NetworkTVShowDetailsMapper
import com.example.tv.repository.NetworkTMDBTVRepository
import com.skydoves.sandwich.ApiResponse
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

sealed interface TVShowDetailsUiState {
    data class Success(
        val show: TVShowDetails
    ) : TVShowDetailsUiState

    data object Error : TVShowDetailsUiState
    data object Loading : TVShowDetailsUiState
}

class TVShowDetailsViewModel @AssistedInject constructor(
    private val repository: NetworkTMDBTVRepository,
    private val mapper: NetworkTVShowDetailsMapper,
    @Assisted
    private val id: Int
): ViewModel() {

    var uiState: TVShowDetailsUiState by mutableStateOf(TVShowDetailsUiState.Loading)
        private set
    init {
        viewModelScope.launch {
            val response = repository.fetchTVDetails(id)

            uiState = when(response) {
                is ApiResponse.Success -> {
                    TVShowDetailsUiState.Success(
                        show = mapper.mapFromNetwork(response.data)
                    )
                }
                else -> {
                    TVShowDetailsUiState.Error
                }
            }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(id: Int): TVShowDetailsViewModel
    }

    companion object {
        fun provideTVShowDetailsViewModelFactory(
            factory: Factory,
            showID: Int
        ): ViewModelProvider.Factory {
            return object: ViewModelProvider.Factory {
                override fun <T: ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(showID) as T
                }
            }
        }
    }
}