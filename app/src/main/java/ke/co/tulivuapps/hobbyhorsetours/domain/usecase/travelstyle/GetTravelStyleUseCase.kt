package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.travelstyle

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hobbyhorsetours.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.TravelStyleRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 27.03.2023
 */

class GetTravelStyleUseCase(
    internal val repository: TravelStyleRepository
) : BaseUseCase<GetTravelStyleUseCase.Params, PagingData<TravelStyleDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<TravelStyleDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { TravelStylePagingSource(repository, param.options) }
        ).flow
    }
}