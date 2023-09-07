package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.travelstyle

import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by brendenozie on 29.03.2023
 */

class DeleteTravelStyleFavoriteUseCase(
    internal val repository: HotelRepository
) : BaseUseCase<DeleteTravelStyleFavoriteUseCase.Params, Unit> {

    data class Params(
        val travelStyleId: Int?
    ) : IParams

    override suspend fun invoke(param: Params): Flow<Unit> {
        param.travelStyleId?.let {
            repository.deleteFavoriteById(param.travelStyleId)
        } ?: kotlin.run {
            repository.deleteFavoriteList()
        }
        return flow { emit(Unit) }
    }
}