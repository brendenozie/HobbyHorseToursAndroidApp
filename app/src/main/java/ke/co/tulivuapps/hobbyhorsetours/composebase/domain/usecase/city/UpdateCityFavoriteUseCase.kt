package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.city

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.extension.toCityFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.CityRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by brendenozie on 27.03.2023
 */

class UpdateCityFavoriteUseCase(
    internal val repository: CityRepository
) : BaseUseCase<UpdateCityFavoriteUseCase.Params, Unit> {

    data class Params(
        val character: CityDto
    ) : IParams

    override suspend fun invoke(param: Params) = flow<Unit> {
        val dto = param.character
        val character = repository.getFavoriteCity(dto.localId ?: 0)
        if (character == null) {
            repository.saveFavoriteCity(dto.toCityFavoriteEntity())
        } else {
            repository.deleteFavoriteCityById(dto.localId ?: 0)
        }
        emit(Unit)
    }
}