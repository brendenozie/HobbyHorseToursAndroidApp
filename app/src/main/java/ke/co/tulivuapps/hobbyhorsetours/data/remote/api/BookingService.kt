package ke.co.tulivuapps.hobbyhorsetours.data.remote.api

import ke.co.tulivuapps.hobbyhorsetours.data.model.booking.BookingInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.booking.BookingResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.Constants
import retrofit2.Response
import retrofit2.http.*

/**
 * Created by brendenozie on 10.03.2023
 */

interface BookingService {

    @GET(Constants.BOOKING_LIST)
    suspend fun getAllBookings(
        @Query(Constants.PARAM_PAGE) page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<BookingResponse>

    @GET(Constants.GET_BOOKING)
    suspend fun getBooking(
        @Path(Constants.PARAM_ID) cityId: Int
    ): Response<BookingInfoResponse>

    @GET(Constants.GET_BOOKING)
    suspend fun getBooking(
        @Url url: String
    ): Response<BookingInfoResponse>

    @GET(Constants.BOOKING_FILTER)
    suspend fun getFilterBooking(
        @Query(Constants.PARAM_PAGE) page: Int,
        @QueryMap options: Map<String, String>? = null
    ): Response<BookingResponse>

    @POST(Constants.POST_BOOKING_LIST)
    suspend fun postBooking(@Body data: BookingInfoResponse): Response<BookingInfoResponse>
}