package ke.co.tulivuapps.hobbyhorsetours.features.screen.booking

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.BookingDto
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.bookings.GetBookingsUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.bookings.BookingsViewState
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by brendenozie on 30.03.2023
 */

@HiltViewModel
class BookingsViewModel @Inject constructor(
    private val getBookingsUseCase: GetBookingsUseCase,
) : BaseViewModel<BookingsViewState, BookingsViewEvent>() {

    private val config = PagingConfig(pageSize = 20)

    init {
        getAllBookings()
    }

    private fun getAllBookings() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            val params = GetBookingsUseCase.Params(config, hashMapOf())
            val pagedFlow = getBookingsUseCase(params).cachedIn(scope = viewModelScope)
            delay(1000)
            setState { currentState.copy(isLoading = false, pagedData = pagedFlow) }
        }
    }

    private fun updateBooking(dto: BookingDto) = viewModelScope.launch {
//        val params = UpdateBookingUseCase.Params(dto)
//        call(updateHotelFavoriteUseCase(params))
    }

    override fun createInitialState() = BookingsViewState()

    override fun onTriggerEvent(event: BookingsViewEvent) {
        viewModelScope.launch {
            when (event) {
                is BookingsViewEvent.UpdateBooking -> updateBooking(event.dto)
            }
        }
    }
}

sealed class BookingsViewEvent : IViewEvent {
    class UpdateBooking(val dto: BookingDto) : BookingsViewEvent()

}