package ke.co.tulivuapps.hobbyhorsetours.composebase.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodesResponse(
    val info: InfoResponse,
    val results: List<EpisodesResultResponse>
) : Parcelable
