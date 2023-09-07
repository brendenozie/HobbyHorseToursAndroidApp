package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.travelstyle

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.extension.toHotelFavoriteDtoList
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.HotelRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by brendenozie on 30.03.2023
 */

class GetTravelStyleFavoritesUseCase(
    internal val repository: HotelRepository
) : BaseUseCase<IParams, List<HotelDto>> {

    override suspend fun invoke(param: IParams) = flow {
        val favorites = repository.getFavoriteList()
        emit(favorites.toHotelFavoriteDtoList())
    }
}