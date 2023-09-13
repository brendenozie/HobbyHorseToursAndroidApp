package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.hotels

import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.extension.toHotelFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.HotelRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by brendenozie on 27.03.2023
 */

class UpdateHotelFavoriteUseCase(
    internal val repository: HotelRepository
) : BaseUseCase<UpdateHotelFavoriteUseCase.Params, Unit> {

    data class Params(
        val character: HotelDto
    ) : IParams

    override suspend fun invoke(param: Params) = flow<Unit> {
        val dto = param.character
        val character = repository.getFavorite(dto.localId ?: 0)
        if (character == null) {
            repository.saveFavorite(dto.toHotelFavoriteEntity())
        } else {
            repository.deleteFavoriteById(dto.localId ?: 0)
        }
        emit(Unit)
    }
}