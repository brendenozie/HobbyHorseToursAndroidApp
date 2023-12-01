package ke.co.tulivuapps.hobbyhorsetours.data.model.dto

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 27.03.2023
 */

@Parcelize
data class CityDto(
    val localId: Int?,
    val id     :  String?,
    val cityName : String,
    val publicId : String,
    val url      : String,
    val status   : String,
    var isFavorite   : Boolean = false
) : Parcelable {
    companion object {
        fun init() = CityDto(
            localId = 1,
            id = null,
            cityName = "",
            publicId = "",
            url = "",
            status = "",
            isFavorite = false,
        )

        fun create(jsonString: String): CityDto? {
            return try {
                Gson().fromJson(jsonString, CityDto::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}