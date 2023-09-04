package ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.travelstyle

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.LocationResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.OriginResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.Status

/**
 * Created by brendenozie on 12.03.2023
 */

data class TravelStyleInfoResponse(
    val created: String?,
    val episode: List<String>?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    val location: LocationResponse?,
    val name: String?,
    val origin: OriginResponse?,
    val species: String?,
    val status: Status?,
    val type: String?,
    val url: String?
)
