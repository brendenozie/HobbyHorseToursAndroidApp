package ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.city

import android.os.Parcelable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.InfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.ResultCity
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 10.03.2023
 */

@Parcelize
data class CityResponse(
    val info: InfoResponse,
    val results: List<ResultCity>
) : Parcelable