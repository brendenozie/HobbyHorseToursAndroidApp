package ke.co.tulivuapps.hobbyhorsetours.data.model.dto

import android.os.Parcelable
import ke.co.tulivuapps.hobbyhorsetours.data.model.img
import kotlinx.parcelize.Parcelize

/**
 * Created by brendenozie on 27.03.2023
 */

@Parcelize
data class DestinationDto(
    val localId: Int?,
    val id: String?,
    val title: String?,
    val description: String?,
    val star: String?,
    val lat: String?,
    val location: String?,
    val lang: String?,
    val price: String?,
    val offer: String?,
    val offerPrice: String?,
    val userEmail: String?,
    val cityId: String?,
    val createdAt: String?,
    val img: List<img>?,
    var isFavorite: Boolean = false
) : Parcelable {
    companion object {
        fun init() = DestinationDto(
            localId = 1,
            id = null,
            title = "Awesome Title",
            description = null,
            star = null,
            lat = null,
            location = null,
            lang = null,
            price = null,
            offer = null,
            offerPrice = null,
            userEmail = null,
            cityId = null,
            createdAt = null,
            img = null,
            isFavorite = false,
        )
    }
}