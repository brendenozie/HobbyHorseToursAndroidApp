package ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.profile

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.data.model.popular.PopularResponse
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewState

/**
 * Created by brendenozie on 19.03.2023
 */

@Stable
data class



ProfileViewState(
    val isLoading: Boolean = false,
    val data: List<PopularResponse>? = null
) : IViewState