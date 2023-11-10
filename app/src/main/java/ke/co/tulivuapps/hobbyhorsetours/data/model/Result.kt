package ke.co.tulivuapps.hobbyhorsetours.data.model

import android.os.Parcelable
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Result(
    val localId: Int? =0,
    val id: String,
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
    val likes: List<String>?=null,
    val views: List<String>?=null,
    val img: List<img>
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