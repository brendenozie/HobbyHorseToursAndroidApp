package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.destinations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.DestinationRepository
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.hotels.GetHotelsFilterUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.hotels.HotelsFilterPagingSource
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 12.04.2023
 */

class GetDestinationsFilterUseCase(
    internal val repository: DestinationRepository
) : BaseUseCase<GetDestinationsFilterUseCase.Params, PagingData<DestinationDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<DestinationDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { DestinationsFilterPagingSource(repository, param.options) }
        ).flow
    }
}