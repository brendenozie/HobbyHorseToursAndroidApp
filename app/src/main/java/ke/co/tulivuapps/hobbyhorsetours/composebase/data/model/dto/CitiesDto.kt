package ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 27.03.2023
 */

@Parcelize
data class CitiesDto(
    val localId: Int?,
    val id     :  String?,
    val cityName : String,
    val publicId : String,
    val url      : String,
    val status   : String,
    val isFavorite   : Boolean = false
) : Parcelable {
    companion object {
        fun init() = CitiesDto(
            localId = 1,
            id = null,
            cityName = "",
            publicId = "",
            url = "",
            status = "",
            isFavorite = false,
        )
    }
}