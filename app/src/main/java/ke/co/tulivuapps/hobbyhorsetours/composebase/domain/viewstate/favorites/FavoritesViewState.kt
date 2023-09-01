package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.favorites

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.FavoriteEntity
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewState

/**
 * Created by brendenozie on 30.03.2023
 */

@Stable
data class FavoritesViewState(
    val favoritesList: List<FavoriteEntity> = emptyList(),
    val favoriteId: Int? = null,
    val isDisplay: Boolean = false,
    val isAllDeleteFavorites: Boolean = false,
    val isLoading: Boolean = false,
) : IViewState