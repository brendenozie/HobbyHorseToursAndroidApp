package ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.bookingdetails

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.data.model.ResultBooking
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewState

/**
 * Created by brendenozie on 13.03.2023
 */
@Stable
data class BookingDetailViewState(
    val isLoading: Boolean = false,
    val data: ResultBooking? = null
) : IViewState