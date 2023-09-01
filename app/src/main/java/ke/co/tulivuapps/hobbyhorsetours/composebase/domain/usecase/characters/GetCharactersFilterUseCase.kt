package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.CharacterDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 12.04.2023
 */

class GetCharactersFilterUseCase(
    internal val repository: CharacterRepository
) : BaseUseCase<GetCharactersFilterUseCase.Params, PagingData<CharacterDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<CharacterDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { CharactersFilterPagingSource(repository, param.options) }
        ).flow
    }
}