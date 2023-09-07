package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.city

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.extension.toCityDtoList
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.CityRepository
import java.io.IOException

/**
 * Created by brendenozie on 12.04.2023
 */

class CityFilterPagingSource(
    internal val repository: CityRepository,
    private val options: Map<String, String>
) : PagingSource<Int, CityDto>() {

    override fun getRefreshKey(state: PagingState<Int, CityDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CityDto> {
        val page = params.key ?: 1
        return try {
            val response = repository.getFilterCitys(page, options)

            val cityList = if (response.isSuccessful) {
                response.body()?.results.orEmpty().toCityDtoList()
            } else {
                emptyList()
            }

            if (cityList.isNotEmpty()) {
                cityList.map {
                    val cityFav = repository.getFavorite(it.localId ?: 0)
                    it.isFavorite = cityFav != null
                }
            }

            LoadResult.Page(
                data = cityList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (cityList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}