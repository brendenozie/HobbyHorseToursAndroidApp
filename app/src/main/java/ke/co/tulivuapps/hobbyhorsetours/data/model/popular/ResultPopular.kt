package ke.co.tulivuapps.hobbyhorsetours.data.model.popular

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultPopular(
    val count : Int,
    val document: ResultHotelPopular
    ) : Parcelable {

    companion object {
        fun create(jsonString: String): ResultPopular? {
            return try {
                Gson().fromJson(jsonString, ResultPopular::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}