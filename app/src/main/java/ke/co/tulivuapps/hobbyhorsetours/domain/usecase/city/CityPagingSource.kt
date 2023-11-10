package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.city

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.extension.toCityDtoList
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.CityRepository
import java.io.IOException


/**
 * Created by brendenozie on 27.03.2023
 */

class CityPagingSource(
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

            val response = repository.getAllCities(page, options)

            val info =if (response.isSuccessful) response.body()?.info else null
            val cityList = if (response.isSuccessful) response.body()?.results.orEmpty().toCityDtoList() else emptyList()

            if (cityList.isNotEmpty()) {
                cityList.map {
                    val cityFav = repository.getFavoriteCity(it.localId ?: 0)
                    it.isFavorite = cityFav != null
                }
            }

            LoadResult.Page(
                data = cityList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (info==null) null else info.next as Int
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}