package ke.co.tulivuapps.hobbyhorsetours.data.model.travelstyle

import android.os.Parcelable
import ke.co.tulivuapps.hobbyhorsetours.data.model.InfoResponse
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 10.03.2023
 */

@Parcelize
data class TravelStyleResponse(
    val info: InfoResponse,
    val results: List<ResultTravelStyle>
) : Parcelable