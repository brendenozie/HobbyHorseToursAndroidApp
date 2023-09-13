package ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.charactersdetail

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewState

/**
 * Created by brendenozie on 13.03.2023
 */
@Stable
data class CharactersDetailViewState(
    val isLoading: Boolean = false,
    val data: Result? = null
) : IViewState