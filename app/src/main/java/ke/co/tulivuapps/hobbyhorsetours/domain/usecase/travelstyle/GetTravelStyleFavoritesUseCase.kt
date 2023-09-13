package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.travelstyle

import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.extension.toTravelStyleFavoriteDtoList
import ke.co.tulivuapps.hobbyhorsetours.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.TravelStyleRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by brendenozie on 30.03.2023
 */

class GetTravelStyleFavoritesUseCase(
    internal val repository: TravelStyleRepository
) : BaseUseCase<IParams, List<TravelStyleDto>> {

    override suspend fun invoke(param: IParams) = flow {
        val favorites = repository.getTravelStyleFavoriteList()
        emit(favorites.toTravelStyleFavoriteDtoList())
    }
}