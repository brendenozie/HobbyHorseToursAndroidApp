package ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.api

import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.hotel.HotelInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.hotel.HotelResponse
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.remote.utils.Constants
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by brendenozie on 10.03.2023
 */

interface HotelService {

    @GET(Constants.HOTEL_LIST)
    suspend fun getAllHotels(
        @Query(Constants.PARAM_PAGE) page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<HotelResponse>

    @GET(Constants.GET_HOTEL)
    suspend fun getHotel(
        @Path(Constants.PARAM_ID) characterId: Int
    ): Response<HotelInfoResponse>

    @GET(Constants.GET_HOTEL)
    suspend fun getHotel(
        @Url url: String
    ): Response<HotelInfoResponse>

    @GET(Constants.HOTEL_FILTER)
    suspend fun getFilterHotel(
        @Query(Constants.PARAM_PAGE) page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<HotelResponse>
}