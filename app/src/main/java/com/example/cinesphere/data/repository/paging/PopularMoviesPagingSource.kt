package com.example.cinesphere.data.repository.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cinesphere.data.remote.models.NetworkMovie
import com.example.cinesphere.data.remote.service.TMDBService
import com.example.cinesphere.data.repository.paging.PopularMoviesPagingSource.PagingConstants.TAG
import javax.inject.Inject
import kotlin.math.max

class PopularMoviesPagingSource @Inject constructor(
    private val api: TMDBService,
): PagingSource<Int, NetworkMovie>() {
    object PagingConstants {
        const val STARTING_KEY: Int = 1
        const val TAG = "POPULAR_MOVIES_PAGING_TAG"

    }

    override fun getRefreshKey(state: PagingState<Int, NetworkMovie>): Int? {
        Log.d(TAG, "getRefreshKey - ${state.anchorPosition}")
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkMovie> {
        val start = params.key ?: PagingConstants.STARTING_KEY

        val response = api.popularMovies(start)

        when (start) {
            PagingConstants.STARTING_KEY -> Log.d(TAG,"PrevKey - ${null}\n")
            else -> Log.d(TAG, ensureValidKey(response.page - 1).toString())
        }

        when(start) {
            response.totalPages -> Log.d(TAG,"NextKey - ${null}\n")
            else -> Log.d(TAG, "NextKey - ${response.page + 1}\n")
        }

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
