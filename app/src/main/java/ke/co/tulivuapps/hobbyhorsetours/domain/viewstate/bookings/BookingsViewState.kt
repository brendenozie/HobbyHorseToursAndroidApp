package ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.bookings

import androidx.compose.runtime.Stable
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.data.model.ResultBooking
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.BookingDto
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewState
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 13.03.2023
 */
@Stable
data class BookingsViewState(
    val isLoading: Boolean = false,
    val pagedData: Flow<PagingData<BookingDto>>? = null,
    val data: List<ResultBooking>? = null,
) : IViewState