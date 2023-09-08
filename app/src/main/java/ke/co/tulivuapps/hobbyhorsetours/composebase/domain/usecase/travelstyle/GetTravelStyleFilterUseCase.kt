package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.travelstyle

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.HotelRepository
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.TravelStyleRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 12.04.2023
 */

class GetTravelStyleFilterUseCase(
    internal val repository: TravelStyleRepository
) : BaseUseCase<GetTravelStyleFilterUseCase.Params, PagingData<TravelStyleDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<TravelStyleDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { TravelStyleFilterPagingSource(repository, param.options) }
        ).flow
    }
}