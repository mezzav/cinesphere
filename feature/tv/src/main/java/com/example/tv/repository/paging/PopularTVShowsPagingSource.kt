package com.example.tv.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.network.TMDBService
import com.example.network.model.NetworkTVShow
import javax.inject.Inject
import kotlin.math.max

class PopularTVShowsPagingSource @Inject constructor(
    private val api: TMDBService,
): PagingSource<Int, NetworkTVShow>() {
    object PagingConstants {
        const val STARTING_KEY: Int = 1
        const val TAG = "POPULAR_TV_PAGING_TAG"
    }

    override fun getRefreshKey(state: PagingState<Int, NetworkTVShow>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetworkTVShow> {
        val start = params.key ?: PagingConstants.STARTING_KEY

        val response = api.popularTVShows(start)

        val shows = response.results

        return LoadResult.Page(
            data = shows,
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
