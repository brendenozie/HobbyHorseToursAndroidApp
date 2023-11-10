package ke.co.tulivuapps.hobbyhorsetours.data.model.popular

import android.os.Parcelable
import com.google.gson.Gson
import ke.co.tulivuapps.hobbyhorsetours.data.model.img
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultHotelPopular(
    val localId: Int? =0,
    val id: objItem,
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
    val cityId: objItem,
//    val createdAt: String,
    val travelStyleId: objItem,
    val likes: List<String>?=null,
    val views: List<String>?=null,
    val img: List<img>?=null
    ) : Parcelable {

    companion object {
        fun create(jsonString: String): ResultHotelPopular? {
            return try {
                Gson().fromJson(jsonString, ResultHotelPopular::class.java)
            } catch (e: Exception) {
                return null
            }
        }
    }
}