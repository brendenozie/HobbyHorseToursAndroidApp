package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.bookings

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.BookingDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.extension.toBookingDtoList
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.BookingsRepository
import java.io.IOException

/**
 * Created by brendenozie on 27.03.2023
 */

class BookingsPagingSource(
    internal val repository: BookingsRepository,
    private val options: Map<String, String>
) : PagingSource<Int, BookingDto>() {

    override fun getRefreshKey(state: PagingState<Int, BookingDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookingDto> {
        val page = params.key ?: 1
        return try {
            val response = repository.getAllBookings(page, options)

            val cityList = if (response.isSuccessful) {
                response.body()?.results.orEmpty().toBookingDtoList()
            } else {
                emptyList()
            }

            LoadResult.Page(
                data = cityList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (cityList.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}