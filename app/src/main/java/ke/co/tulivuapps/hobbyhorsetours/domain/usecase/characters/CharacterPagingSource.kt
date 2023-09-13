package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CharacterDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.extension.toCharacterDtoList
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.CharacterRepository
import java.io.IOException

/**
 * Created by brendenozie on 27.03.2023
 */

class CharacterPagingSource(
    internal val repository: CharacterRepository,
    private val options: Map<String, String>
) : PagingSource<Int, CharacterDto>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterDto> {
        val page = params.key ?: 1
        return try {
            val response = repository.getAllCharacters(page, options)

            val characterList = if (response.isSuccessful) {
                response.body()?.results.orEmpty().toCharacterDtoList()
            } else {
                emptyList()
            }

            if (characterList.isNotEmpty()) {
                characterList.map {
                    val characterFav = repository.getFavorite(it.id ?: 0)
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