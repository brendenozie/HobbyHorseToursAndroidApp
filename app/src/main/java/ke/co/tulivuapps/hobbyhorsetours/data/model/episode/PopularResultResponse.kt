package ke.co.tulivuapps.hobbyhorsetours.data.model.episode

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 19.03.2023
 */

@Parcelize
data class PopularResultResponse(
    val id: Int?,
    val name: String?,
    @SerializedName("air_date")
    val airDate: String?,
    val episode: String?,
    val characters: List<String>?,
    val created: String?,
    val url: String?
) : Parcelable {

    fun convertToJSON(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun create(jsonString: String): PopularResultResponse? {
            return try {
                Gson().fromJson(jsonString, PopularResultResponse::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}