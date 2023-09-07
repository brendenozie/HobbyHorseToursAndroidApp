package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.travelstyle

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 13.03.2023
 */
@Stable
data class TravelStyleViewState(
    val isLoading: Boolean = false,
    val pagedData: Flow<PagingData<TravelStyleDto>>? = null,
    val data: List<Result>? = null,
) : IViewState