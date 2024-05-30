package com.example.tv.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.example.model.TVShow
import com.example.tv.mappers.NetworkTVShowMapper
import com.example.tv.repository.paging.OnTheAirTVShowsPagingSource
import com.example.tv.repository.paging.PopularTVShowsPagingSource
import com.example.tv.repository.paging.TopRatedTVShowsPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class TVOverviewViewModel @Inject constructor(
    private val onTheAirTVShowsPagingSource: OnTheAirTVShowsPagingSource,
    private val topRatedTVShowsPagingSource: TopRatedTVShowsPagingSource,
    private val popularTVShowsPagingSource: PopularTVShowsPagingSource,
    private val mapper: NetworkTVShowMapper
) : ViewModel() {
    val onTheAirTVShows: Flow<PagingData<TVShow>> = Pager(
        config = PagingConfig(
            pageSize = 1,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = { onTheAirTVShowsPagingSource }
    ).flow.map { pagingData ->
        pagingData.filter { networkTVShow ->
            !networkTVShow.poster.isNullOrBlank()
        }.map { networkTVShow ->
            mapper.mapFromNetwork(networkTVShow)
        }
    }.cachedIn(viewModelScope)

    val topRatedTVShows: Flow<PagingData<TVShow>> = Pager(
        config = PagingConfig(
            pageSize = 1,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = { topRatedTVShowsPagingSource }
    ).flow.map { pagingData ->
        pagingData.filter { networkTVShow ->
            !networkTVShow.poster.isNullOrBlank()
        }.map { networkTVShow ->
            mapper.mapFromNetwork(networkTVShow)
        }
    }.cachedIn(viewModelScope)

    val popularTVShows: Flow<PagingData<TVShow>> = Pager(
        config = PagingConfig(
            pageSize = 1,
            enablePlaceholders = false,
        ),
        pagingSourceFactory = { popularTVShowsPagingSource }
    ).flow.map { pagingData ->
        pagingData.filter { networkTVShow ->
            !networkTVShow.poster.isNullOrBlank()
        }.map { networkTVShow ->
            mapper.mapFromNetwork(networkTVShow)
        }
    }.cachedIn(viewModelScope)
}