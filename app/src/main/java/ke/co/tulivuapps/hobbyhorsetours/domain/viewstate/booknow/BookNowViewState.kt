package ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.booknow

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.data.model.booking.BookingInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewState

/**
 * Created by brendenozie on 13.03.2023
 */
@Stable
data class BookNowViewState(
    val isLoading: Boolean = false,
    val data: Result? = null,
    val bookingResponse: BookingInfoResponse? = null,
) : IViewState