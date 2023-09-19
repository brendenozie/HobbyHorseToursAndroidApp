package ke.co.tulivuapps.hobbyhorsetours.data.model.booking

import ke.co.tulivuapps.hobbyhorsetours.data.model.img

/**
 * Created by brendenozie on 12.03.2023
 */

data class BookingInfoResponse(
    val id : String,
    val createdAt : String,
    val sessionId : String?,
    val hotellId   :  String,
    val description : String,
    val startDate  : String,
    val endDate    : String,
    val img         : img?,
    val lat         :Float,
    val location    :String,
    val long        :Float,
    val price       :String,
    val status      :String,
    val star        :Float,
    val title       :String,
    val total       :Float?,
    val userEmail   :String,
    val cityId      :String
)
