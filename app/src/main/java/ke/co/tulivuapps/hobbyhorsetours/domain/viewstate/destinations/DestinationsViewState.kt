package ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.destinations

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CharacterDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 13.03.2023
 */
@Stable
data class DestinationsViewState(
    val isLoading: Boolean = false,
    val pagedData: Flow<PagingData<DestinationDto>>? = null,
    val data: List<Result>? = null,
) : IViewState