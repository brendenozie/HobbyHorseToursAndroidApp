package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.city

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 12.04.2023
 */

class GetCityFilterUseCase(
    internal val repository: CityRepository
) : BaseUseCase<GetCityFilterUseCase.Params, PagingData<CityDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<CityDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { CityFilterPagingSource(repository, param.options) }
        ).flow
    }
}