package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.city

import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by brendenozie on 29.03.2023
 */

class DeleteCityFavoriteUseCase(
    internal val repository: CityRepository
) : BaseUseCase<DeleteCityFavoriteUseCase.Params, Unit> {

    data class Params(
        val cityId: Int?
    ) : IParams

    override suspend fun invoke(param: Params): Flow<Unit> {
        param.cityId?.let {
            repository.deleteFavoriteById(param.cityId)
        } ?: kotlin.run {
            repository.deleteFavoriteList()
        }
        return flow { emit(Unit) }
    }
}