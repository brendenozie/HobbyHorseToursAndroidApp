package ke.co.tulivuapps.hobbyhorsetours.data.model.hotel

import android.os.Parcelable
import ke.co.tulivuapps.hobbyhorsetours.data.model.InfoResponse
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 10.03.2023
 */

@Parcelize
data class HotelResponse(
    val info: InfoResponse,
    val results: List<ResultHotel>
) : Parcelable