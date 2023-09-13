package ke.co.tulivuapps.hobbyhorsetours.data.model.destination

import android.os.Parcelable
import ke.co.tulivuapps.hobbyhorsetours.data.model.InfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.Result
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 10.03.2023
 */

@Parcelize
data class DestinationResponse(
    val info: InfoResponse,
    val results: List<Result>
) : Parcelable