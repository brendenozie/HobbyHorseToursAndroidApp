package ke.co.tulivuapps.hobbyhorsetours.data.remote.source

import ke.co.tulivuapps.hobbyhorsetours.data.model.booking.BookingInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.booking.BookingResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

/**
 * Created by brendenozie on 12.03.2023
 */

interface BookingsRemoteDataSource {
    suspend fun getAllBookings(page: Int, options: Map<String, String>): Response<BookingResponse>
    suspend fun getFilterBookings(page: Int, options: Map<String, String>): Response<BookingResponse>
    suspend fun getBooking(cityId: Int): Flow<DataState<BookingInfoResponse>>
    suspend fun getBooking(url: String): Flow<DataState<BookingInfoResponse>>
    suspend fun setBooking(data: BookingInfoResponse): Flow<DataState<BookingInfoResponse>>
}