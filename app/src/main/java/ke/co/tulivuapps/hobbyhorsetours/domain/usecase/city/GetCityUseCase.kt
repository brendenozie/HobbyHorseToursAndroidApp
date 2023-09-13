package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.city

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 27.03.2023
 */

class GetCityUseCase(
    internal val repository: CityRepository
) : BaseUseCase<GetCityUseCase.Params, PagingData<CityDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<CityDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { CityPagingSource(repository, param.options) }
        ).flow
    }
}