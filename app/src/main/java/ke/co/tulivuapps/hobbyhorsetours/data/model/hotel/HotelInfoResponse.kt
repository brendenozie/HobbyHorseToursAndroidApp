package ke.co.tulivuapps.hobbyhorsetours.data.model.hotel

/**
 * Created by brendenozie on 12.03.2023
 */

data class HotelInfoResponse(
    val localId     :     Int ,
    val id         :   String     ,
    val title        : String,
    val description  : String,
    val star         : Float,
    val img          : String,
    val lat          : Float,
    val location     : String,
    val long         : Float,
    val price        : Float ,
    val offer        : Boolean,
    val offerPrice   : Float   ,
    val userEmail    : String,
    val cityId       : String ,
    val createdAt    : String ,
    val travelStyleId : String   ,
)
