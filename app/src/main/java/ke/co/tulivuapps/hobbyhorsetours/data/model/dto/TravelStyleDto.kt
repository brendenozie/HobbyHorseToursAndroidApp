package ke.co.tulivuapps.hobbyhorsetours.data.model.dto

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 27.03.2023
 */

@Parcelize
data class TravelStyleDto(
    val localId: Int?,
    val id: String?,
    val styleName : String?,
    val publicId : String?,
    val url      : String?,
    val status   : String?,
    val img: String?,
    val createdAt: String?,
    var isFavorite: Boolean = false
) : Parcelable {
    companion object {
        fun init() = TravelStyleDto(
            localId = 1,
            id = null,
            styleName = "",
            publicId = "",
            url = "",
            status = "",
            img = "",
            createdAt = "",
            isFavorite = false,
        )

        fun create(jsonString: String): TravelStyleDto? {
            return try {
                Gson().fromJson(jsonString, TravelStyleDto::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}