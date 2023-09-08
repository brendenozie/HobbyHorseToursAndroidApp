package ke.co.tulivuapps.hobbyhorsetours.composebase.data.model

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultTravelStyle(
    val localId: Int? =0,
    val id: String?,
    val styleName : String,
    val publicId : String,
    val url      : String,
    val status   : String,
    val createdAt   : String,
    val img: String
    ) : Parcelable {

    companion object {
        fun create(jsonString: String): ResultTravelStyle? {
            return try {
                Gson().fromJson(jsonString, ResultTravelStyle::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}