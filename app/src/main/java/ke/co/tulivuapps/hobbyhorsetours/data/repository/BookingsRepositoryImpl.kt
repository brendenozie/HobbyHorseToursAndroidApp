package ke.co.tulivuapps.hobbyhorsetours.data.repository

import ke.co.tulivuapps.hobbyhorsetours.data.model.booking.BookingInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.booking.BookingResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.source.BookingsRemoteDataSource
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.BookingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by brendenozie on 12.03.2023
 */

class BookingsRepositoryImpl @Inject constructor(
    private val cityRemoteDataSource: BookingsRemoteDataSource
) : BookingsRepository {

    override suspend fun getAllBookings(
        page: Int,
        options: Map<String, String>
    ): Response<BookingResponse> =
        cityRemoteDataSource.getAllBookings(page = page, options = options)

    override fun getBooking(cityId: Int): Flow<DataState<BookingInfoResponse>> = flow {
        emitAll(cityRemoteDataSource.getBooking(cityId = cityId))
    }

    override fun getBooking(url: String): Flow<DataState<BookingInfoResponse>> = flow {
        emitAll(cityRemoteDataSource.getBooking(url = url))
    }

    override suspend fun getFilterBooking(
        page: Int,
        options: Map<String, String>
    ): Response<BookingResponse> = cityRemoteDataSource.getFilterBookings(page, options)

    override suspend fun bookHotel(bookingInfoResponse: BookingInfoResponse): Flow<DataState<BookingInfoResponse>> = flow {
            emitAll(cityRemoteDataSource.setBooking(bookingInfoResponse))
        }

}