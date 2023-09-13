package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.destinations

import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.extension.toDestinationFavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by brendenozie on 27.03.2023
 */

class UpdateDestinationFavoriteUseCase(
    internal val repository: DestinationRepository
) : BaseUseCase<UpdateDestinationFavoriteUseCase.Params, Unit> {

    data class Params(
        val character: DestinationDto
    ) : IParams

    override suspend fun invoke(param: Params) = flow<Unit> {
        val dto = param.character
        val character = repository.getFavorite(dto.localId ?: 0)
        if (character == null) {
            repository.saveFavorite(dto.toDestinationFavoriteEntity())
        } else {
            repository.deleteFavoriteById(dto.localId ?: 0)
        }
        emit(Unit)
    }
}