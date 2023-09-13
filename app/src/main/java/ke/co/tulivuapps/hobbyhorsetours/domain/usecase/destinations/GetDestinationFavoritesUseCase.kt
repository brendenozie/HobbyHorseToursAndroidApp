package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.destinations

import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CharacterDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.extension.toDestinationFavoriteDtoList
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.extension.toFavoriteDtoList
import ke.co.tulivuapps.hobbyhorsetours.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.CharacterRepository
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.DestinationRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by brendenozie on 30.03.2023
 */

class GetDestinationFavoritesUseCase(
    internal val repository: DestinationRepository
) : BaseUseCase<IParams, List<DestinationDto>> {

    override suspend fun invoke(param: IParams) = flow {
        val favorites = repository.getFavoriteList()
        emit(favorites.toDestinationFavoriteDtoList())
    }
}