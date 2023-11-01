package ke.co.tulivuapps.hobbyhorsetours.data.remote.source.impl

import ke.co.tulivuapps.hobbyhorsetours.data.model.booking.BookingInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.booking.BookingResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.api.BookingService
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.BookingsRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by brendenozie on 12.03.2023
 */
class BookingRemoteDataSourceImpl @Inject constructor(
    private val cityService: BookingService,
) :
    BaseRemoteDataSource(), BookingsRemoteDataSource {

    override suspend fun getAllBookings( page: Int, options: Map<String, String>): Response<BookingResponse> =
        cityService.getAllBookings(page, options)

    override suspend fun getFilterBookings( page: Int, options: Map<String, String> ): Response<BookingResponse> =
        cityService.getFilterBooking(page, options)

    override suspend fun getBooking(cityId: Int): Flow<DataState<BookingInfoResponse>> =
        getResult {
            cityService.getBooking(cityId = cityId)
        }

    override suspend fun getBooking(url: String): Flow<DataState<BookingInfoResponse>> =
        getResult {
            cityService.getBooking(url)
        }

    override suspend fun setBooking(data: BookingInfoResponse): Flow<DataState<BookingInfoResponse>> =
        getResult {
            cityService.postBooking(data)
        }


}
