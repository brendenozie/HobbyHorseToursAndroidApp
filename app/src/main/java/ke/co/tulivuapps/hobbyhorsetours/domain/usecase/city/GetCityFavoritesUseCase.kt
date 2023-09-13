package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.city

import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.extension.toCityFavoriteDtoList
import ke.co.tulivuapps.hobbyhorsetours.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.CityRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by brendenozie on 30.03.2023
 */

class GetCityFavoritesUseCase(
    internal val repository: CityRepository
) : BaseUseCase<IParams, List<CityDto>> {

    override suspend fun invoke(param: IParams) = flow {
        val favorites = repository.getCityFavoriteList()
        emit(favorites.toCityFavoriteDtoList())
    }
}