package ke.co.tulivuapps.hobbyhorsetours.features.screen.booking

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.local.DataStoreOperation
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.BookingDto
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.bookings.GetBookingsUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.bookings.BookingsViewState
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by brendenozie on 30.03.2023
 */

@HiltViewModel
class BookingsViewModel @Inject constructor(
    private val getBookingsUseCase: GetBookingsUseCase,
    private val dataStoreOperation: DataStoreOperation
) : BaseViewModel<BookingsViewState, BookingsViewEvent>() {

    private val config = PagingConfig(pageSize = 20)

    var selectedLogin :Boolean =  false

    init {
        viewModelScope.launch(Dispatchers.IO) {
            selectedLogin = dataStoreOperation.readOnLoginState().first()
            getAllBookings()
        }
    }

    private fun getAllBookings() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreOperation.readEmailState().collect{
                if (it.isNotEmpty()) {
                    val options = hashMapOf<String, String>()
                    options["userEmail"] = it
                    setState { currentState.copy(isLoading = true) }
                    val params = GetBookingsUseCase.Params(config, options)

                    val pagedFlow =
                        async { getBookingsUseCase(params).cachedIn(scope = viewModelScope) }

                    // Wait for all requests to finish
                    val bookingResponse = pagedFlow.await()

                    setState {
                        currentState.copy(
                            isLoading = false,
                            pagedData = bookingResponse,
                        )
                    }
                }
            }

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
                else -> {}
            }
        }
    }
}

sealed class BookingsViewEvent : IViewEvent {
    class UpdateBooking(val dto: BookingDto) : BookingsViewEvent()

}