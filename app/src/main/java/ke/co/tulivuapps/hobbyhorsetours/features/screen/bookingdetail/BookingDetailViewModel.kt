package ke.co.tulivuapps.hobbyhorsetours.features.screen.bookingdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.local.DataStoreOperation
import ke.co.tulivuapps.hobbyhorsetours.data.model.booking.ResultBooking
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.bookingdetails.BookingDetailViewState
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by brendenozie on 13.03.2023
 */
@HiltViewModel
class BookingDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dataStoreOperation: DataStoreOperation
) : BaseViewModel<BookingDetailViewState, BookingDetailViewEvent>() {

    private val _onLoginCompleted = MutableStateFlow(false)
    val onLoginCompleted: StateFlow<Boolean> = _onLoginCompleted

    init {
        savedStateHandle.get<String>("bookingDetail")?.let {
            setState { currentState.copy(isLoading = false, data = ResultBooking.create(it)) }
        } ?: kotlin.run {
           setEvent(BookingDetailViewEvent.SnackBarError("Something went wrong"))
        }

        viewModelScope.launch(Dispatchers.IO) {
            _onLoginCompleted.value =
                dataStoreOperation.readOnLoginState().stateIn(viewModelScope).value
        }
    }

    override fun createInitialState() = BookingDetailViewState()
    override fun onTriggerEvent(event: BookingDetailViewEvent) {}
}

sealed class BookingDetailViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : BookingDetailViewEvent()
}