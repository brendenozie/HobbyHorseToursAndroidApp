package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.favorite

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.CharacterDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.extension.toFavoriteDtoList
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.flow

/**
 * Created by brendenozie on 30.03.2023
 */

class GetFavoritesUseCase(
    internal val repository: CharacterRepository
) : BaseUseCase<IParams, List<CharacterDto>> {

    override suspend fun invoke(param: IParams) = flow {
        val favorites = repository.getFavoriteList()
        emit(favorites.toFavoriteDtoList())
    }
}