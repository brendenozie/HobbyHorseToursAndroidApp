package ke.co.tulivuapps.hobbyhorsetours.features.screen.bookingdetail

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.model.ResultBooking
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.bookingdetails.BookingDetailViewState
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by brendenozie on 13.03.2023
 */
@HiltViewModel
class BookingDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<BookingDetailViewState, BookingDetailViewEvent>() {

    init {
        savedStateHandle.get<String>("bookingDetail")?.let {
            setState { currentState.copy(isLoading = false, data = ResultBooking.create(it)) }
        } ?: kotlin.run {
           setEvent(BookingDetailViewEvent.SnackBarError("Something went wrong"))
        }
    }

    override fun createInitialState() = BookingDetailViewState()
    override fun onTriggerEvent(event: BookingDetailViewEvent) {}
}

sealed class BookingDetailViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : BookingDetailViewEvent()
}