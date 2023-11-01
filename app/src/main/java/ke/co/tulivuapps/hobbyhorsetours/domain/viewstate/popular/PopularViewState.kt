package ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.popular

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.data.model.popular.ResultPopular
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewState

/**
 * Created by brendenozie on 19.03.2023
 */

@Stable
data class



PopularViewState(
    val isLoading: Boolean = false,
    val data: List<ResultPopular>? = null
) : IViewState