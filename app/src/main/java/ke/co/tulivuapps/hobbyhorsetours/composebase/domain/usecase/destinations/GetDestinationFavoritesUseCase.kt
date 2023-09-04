package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.destinations

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.CharacterDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.extension.toDestinationFavoriteDtoList
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.extension.toFavoriteDtoList
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.CharacterRepository
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.DestinationRepository
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