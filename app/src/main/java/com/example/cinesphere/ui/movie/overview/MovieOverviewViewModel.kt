package com.example.cinesphere.ui.movie.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.cinesphere.data.model.Movie
import com.example.cinesphere.data.repository.paging.NowPlayingMoviesPagingSource
import com.example.cinesphere.data.repository.paging.PopularMoviesPagingSource
import com.example.cinesphere.data.repository.paging.UpcomingMoviesPagingSource
import com.example.cinesphere.domain.FormatTMDBUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
@HiltViewModel
class MovieOverviewViewModel @Inject constructor(
    private val upcomingMoviesPagingSource: UpcomingMoviesPagingSource,
    private val popularMoviesPagingSource: PopularMoviesPagingSource,
    private val nowPlayingMoviesPagingSource: NowPlayingMoviesPagingSource,
    private val formatTMDBUrlUseCase: FormatTMDBUrlUseCase
) : ViewModel() {
        val upcomingMovies: Flow<PagingData<Movie>> = Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { upcomingMoviesPagingSource }
        ).flow.map{ pagingData ->
            pagingData.filter { networkMovie ->
                !networkMovie.poster.isNullOrBlank()
            }.map { networkMovie ->
                Movie(
                    id = networkMovie.id,
                    adult = networkMovie.adult,
                    backdropUrl = networkMovie.backdrop?.let { formatTMDBUrlUseCase(1280, it) },
                    posterUrl = networkMovie.poster?.let { formatTMDBUrlUseCase(342, it) },
                    overview = networkMovie.overview,
                    title = networkMovie.title
                )
            }
        }.cachedIn(viewModelScope)

        val popularMovies: Flow<PagingData<Movie>> = Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { popularMoviesPagingSource }
        ).flow.map{ pagingData ->
            pagingData.filter{ networkMovie ->
                !networkMovie.poster.isNullOrBlank()
            }.map { networkMovie ->
                Movie(
                    id = networkMovie.id,
                    adult = networkMovie.adult,
                    backdropUrl = networkMovie.backdrop?.let { formatTMDBUrlUseCase(1280, it) },
                    posterUrl = networkMovie.poster?.let { formatTMDBUrlUseCase(342, it) },
                    overview = networkMovie.overview,
                    title = networkMovie.title
                )
            }
        }.cachedIn(viewModelScope)

    val nowPlayingMovies: Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = 1,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = { nowPlayingMoviesPagingSource }
    ).flow.map{ pagingData ->
        pagingData.filter{ networkMovie ->
            !networkMovie.poster.isNullOrBlank()
        }.map { networkMovie ->
            Movie(
                id = networkMovie.id,
                adult = networkMovie.adult,
                backdropUrl = networkMovie.backdrop?.let { formatTMDBUrlUseCase(1280, it) },
                posterUrl = networkMovie.poster?.let { formatTMDBUrlUseCase(342, it) },
                overview = networkMovie.overview,
                title = networkMovie.title
            )
        }
    }.cachedIn(viewModelScope)
}