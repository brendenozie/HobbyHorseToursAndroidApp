package ke.co.tulivuapps.hobbyhorsetours.data.model.hotel

import android.os.Parcelable
import com.google.gson.Gson
import ke.co.tulivuapps.hobbyhorsetours.data.model.img
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultHotel(
    val localId: Int? =0,
    val id: String?=null,
    val title: String,
    val description: String,
    val star: String,
    val lat: String,
    val location: String,
    val long: String,
    val price: String,
    val offer: String,
    val offerPrice: String,
    val userEmail: String,
    val cityId: String,
    val createdAt: String,
    val travelStyleId: String = "",
    val likes: List<String>?=null,
    val views: List<String>?=null,
    val img: List<img>?=null
    ) : Parcelable {

    companion object {
        fun create(jsonString: String): ResultHotel? {
            return try {
                Gson().fromJson(jsonString, ResultHotel::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}