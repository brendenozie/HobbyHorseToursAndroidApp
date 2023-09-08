package ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto

import android.os.Parcelable
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.img
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 27.03.2023
 */

@Parcelize
data class HotelDto(
    val localId: Int?,
    val id: String?,
    val title: String,
    val description: String,
    val star: String,
    val lat: String,
    val location: String,
    val lang: String,
    val price: String,
    val offer: String,
    val offerPrice: String,
    val userEmail: String,
    val cityId: String,
    val createdAt: String,
    val travelStyleId: String,
    val img: List<img>?,
    var isFavorite: Boolean = false
) : Parcelable {
    companion object {
        fun init() = HotelDto(
            localId = 1,
            id = null,
            title = "",
            description = "",
            star = "",
            lat = "",
            location = "",
            lang = "",
            price = "",
            offer = "",
            offerPrice = "",
            userEmail = "",
            cityId = "",
            createdAt = "",
            img = null,
            travelStyleId = "",
            isFavorite = false,
        )
    }
}