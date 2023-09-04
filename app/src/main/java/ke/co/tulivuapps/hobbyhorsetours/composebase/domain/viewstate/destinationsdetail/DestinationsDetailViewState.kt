package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.destinationsdetail

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewState

/**
 * Created by brendenozie on 13.03.2023
 */
@Stable
data class DestinationsDetailViewState(
    val isLoading: Boolean = false,
    val data: Result? = null
) : IViewState