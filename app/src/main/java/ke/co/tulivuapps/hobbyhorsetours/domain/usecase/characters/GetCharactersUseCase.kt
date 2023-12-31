package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.characters

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CharacterDto
import ke.co.tulivuapps.hobbyhorsetours.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.CharacterRepository
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.hotels.GetHotelsUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.hotels.HotelPagingSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 27.03.2023
 */

class GetCharactersUseCase(
    internal val repository: CharacterRepository
) : BaseUseCase<GetCharactersUseCase.Params, PagingData<CharacterDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<CharacterDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { CharacterPagingSource(repository, param.options) }
        ).flow
    }
}