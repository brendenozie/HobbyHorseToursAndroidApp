package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.travelstyle

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.extension.toHotelFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.HotelRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by brendenozie on 27.03.2023
 */

class UpdateTravelStyleFavoriteUseCase(
    internal val repository: HotelRepository
) : BaseUseCase<UpdateTravelStyleFavoriteUseCase.Params, Unit> {

    data class Params(
        val travelStyle: HotelDto
    ) : IParams

    override suspend fun invoke(param: Params) = flow<Unit> {
        val dto = param.travelStyle
        val travelStyle = repository.getFavorite(dto.localId ?: 0)
        if (travelStyle == null) {
            repository.saveFavorite(dto.toHotelFavoriteEntity())
        } else {
            repository.deleteFavoriteById(dto.localId ?: 0)
        }
        emit(Unit)
    }
}