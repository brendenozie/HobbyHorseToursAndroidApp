package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.travelstyle

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.extension.toTravelStyleDtoList
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.TravelStyleRepository
import java.io.IOException

/**
 * Created by brendenozie on 12.04.2023
 */

class TravelStyleFilterPagingSource(
    internal val repository: TravelStyleRepository,
    private val options: Map<String, String>
) : PagingSource<Int, TravelStyleDto>() {

    override fun getRefreshKey(state: PagingState<Int, TravelStyleDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TravelStyleDto> {
        val page = params.key ?: 1
        return try {
            val response = repository.getFilterTravelStyle(page, options)

            val info =if (response.isSuccessful) response.body()?.info else null
            val travelStyleList = if (response.isSuccessful) {
                response.body()?.results.orEmpty().toTravelStyleDtoList()
            } else {
                emptyList()
            }

            if (travelStyleList.isNotEmpty()) {
                travelStyleList.map {
                    val travelStyleFav = repository.getFavoriteTravelStyle(it.localId ?: 0)
                    it.isFavorite = travelStyleFav != null
                }
            }

            LoadResult.Page(
                data = travelStyleList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (info==null) null else info.next as Int
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}