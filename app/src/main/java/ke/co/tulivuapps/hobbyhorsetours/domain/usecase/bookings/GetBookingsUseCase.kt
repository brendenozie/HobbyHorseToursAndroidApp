package ke.co.tulivuapps.hobbyhorsetours.domain.usecase.bookings

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.BookingDto
import ke.co.tulivuapps.hobbyhorsetours.domain.base.BaseUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.base.IParams
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.BookingsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 27.03.2023
 */

class GetBookingsUseCase(
    internal val repository: BookingsRepository
) : BaseUseCase<GetBookingsUseCase.Params, PagingData<BookingDto>> {

    data class Params(
        val pagingConfig: PagingConfig,
        val options: Map<String, String>
    ) : IParams

    override suspend fun invoke(param: Params): Flow<PagingData<BookingDto>> {
        return Pager(
            config = param.pagingConfig,
            pagingSourceFactory = { BookingsPagingSource(repository, param.options) }
        ).flow
    }
}