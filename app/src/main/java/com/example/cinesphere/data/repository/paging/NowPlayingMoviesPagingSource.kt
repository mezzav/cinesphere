package com.example.cinesphere.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cinesphere.data.remote.models.NetworkMovie
import com.example.cinesphere.data.remote.service.TMDBService
import javax.inject.Inject
import kotlin.math.max

class NowPlayingMoviesPagingSource @Inject constructor(
    private val api: TMDBService,
): PagingSource<Int, NetworkMovie>() {
    object PagingConstants {
        const val STARTING_KEY: Int = 1
        const val TAG = "NOW_PLAYING_MOVIES_PAGING_TAG"
    }

    override fun getRefreshKey(state: PagingState<Int, NetworkMovie>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkMovie> {
        val start = params.key ?: PagingConstants.STARTING_KEY

        val response = api.nowPlayingMovies(start)

        val movies = response.results

        return LoadResult.Page(
            data = movies,
            prevKey = when (start) {
                PagingConstants.STARTING_KEY -> null
                else -> ensureValidKey(response.page - 1)
            },
            nextKey = when(start) {
                response.totalPages -> null
                else -> response.page + 1
            }
        )
    }

    private fun ensureValidKey(key: Int) = max(PagingConstants.STARTING_KEY, key)
}
