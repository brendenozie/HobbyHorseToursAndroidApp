package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.destinations

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.extension.toDestinationDtoList
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.DestinationRepository
import java.io.IOException

/**
 * Created by brendenozie on 12.04.2023
 */

class DestinationsFilterPagingSource(
    internal val repository: DestinationRepository,
    private val options: Map<String, String>
) : PagingSource<Int, DestinationDto>() {

    override fun getRefreshKey(state: PagingState<Int, DestinationDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DestinationDto> {
        val page = params.key ?: 1
        return try {
            val response = repository.getFilterDestinations(page, options)

            val characterList = if (response.isSuccessful) {
                response.body()?.results.orEmpty().toDestinationDtoList()
            } else {
                emptyList()
            }

            if (characterList.isNotEmpty()) {
                characterList.map {
                    val characterFav = repository.getFavorite(it.localId ?: 0)
                    it.isFavorite = characterFav != null
                }
            }

            LoadResult.Page(
                data = characterList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (characterList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}