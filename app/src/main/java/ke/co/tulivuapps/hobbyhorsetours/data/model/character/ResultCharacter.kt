package ke.co.tulivuapps.hobbyhorsetours.data.model.character

import android.os.Parcelable
import com.google.gson.Gson
import ke.co.tulivuapps.hobbyhorsetours.data.model.LocationResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.OriginResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.data.model.Status
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultCharacter(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationResponse,
    val name: String,
    val origin: OriginResponse,
    val species: String,
    val status: Status?,
    val type: String,
    val url: String
) : Parcelable {

    companion object {
        fun create(jsonString: String): Result? {
            return try {
                Gson().fromJson(jsonString, Result::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}