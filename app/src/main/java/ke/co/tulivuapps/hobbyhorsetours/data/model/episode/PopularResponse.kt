package ke.co.tulivuapps.hobbyhorsetours.data.model.episode

import android.os.Parcelable
import ke.co.tulivuapps.hobbyhorsetours.data.model.InfoResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularResponse(
    val info: InfoResponse,
    val results: List<PopularResultResponse>
) : Parcelable
