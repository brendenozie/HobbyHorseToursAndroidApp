package ke.co.tulivuapps.hobbyhorsetours.data.model.booking

import android.os.Parcelable
import com.google.gson.Gson
import ke.co.tulivuapps.hobbyhorsetours.data.model.img
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultBooking(
    val id: String,
    val createdAt: String,
    val sessionId: String,
    val hotellId: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val img: img?,
    val lat: String,
    val location: String,
    val long: String,
    val price: String,
    val status: String,
    val star: String,
    val title: String,
    val total: String,
    val userEmail: String,
    val cityId: String
    ) : Parcelable {

    companion object {
        fun create(jsonString: String): ResultBooking? {
            return try {
                Gson().fromJson(jsonString, ResultBooking::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}