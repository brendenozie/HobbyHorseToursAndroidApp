package ke.co.tulivuapps.hobbyhorsetours.data.model.booking

import android.os.Parcelable
import ke.co.tulivuapps.hobbyhorsetours.data.model.InfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.ResultBooking
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 10.03.2023
 */

@Parcelize
data class BookingResponse(
    val info: InfoResponse,
    val results: List<ResultBooking>
) : Parcelable