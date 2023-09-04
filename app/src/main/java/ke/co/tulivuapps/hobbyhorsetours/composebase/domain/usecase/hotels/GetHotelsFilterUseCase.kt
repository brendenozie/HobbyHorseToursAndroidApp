package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.hotels

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 12.04.2023
 */

class GetHotelsFilterUseCase(
    internal val repository: HotelRepository
) : BaseUseCase<GetHotelsFilterUseCase.Params, PagingData<HotelDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<HotelDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { HotelsFilterPagingSource(repository, param.options) }
        ).flow
    }
}