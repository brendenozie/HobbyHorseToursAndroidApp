package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.travelstyle

import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.extension.toTravelStyleFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.TravelStyleRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by brendenozie on 27.03.2023
 */

class UpdateTravelStyleFavoriteUseCase(
    internal val repository: TravelStyleRepository
) : BaseUseCase<UpdateTravelStyleFavoriteUseCase.Params, Unit> {

    data class Params(
        val travelStyle: TravelStyleDto
    ) : IParams

    override suspend fun invoke(param: Params) = flow<Unit> {
        val dto = param.travelStyle
        val travelStyle = repository.getFavoriteTravelStyle(dto.localId ?: 0)
        if (travelStyle == null) {
            repository.saveFavoriteTravelStyle(dto.toTravelStyleFavoriteEntity())
        } else {
            repository.deleteFavoriteTravelStyleById(dto.localId ?: 0)
        }
        emit(Unit)
    }
}