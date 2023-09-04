package ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.hotel

import android.os.Parcelable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.InfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.ResultHotel
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 10.03.2023
 */

@Parcelize
data class HotelResponse(
    val info: InfoResponse,
    val results: List<ResultHotel>
) : Parcelable