package ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.episode

import android.os.Parcelable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.InfoResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodesResponse(
    val info: InfoResponse,
    val results: List<EpisodesResultResponse>
) : Parcelable
