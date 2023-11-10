package ke.co.tulivuapps.hobbyhorsetours.features.screen.booknow

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.local.DataStoreOperation
import ke.co.tulivuapps.hobbyhorsetours.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.data.model.booking.BookingInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.BookingDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.user.UserInfoResponse
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.BookingsRepository
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.booknow.BookNowViewState
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import ke.co.tulivuapps.hobbyhorsetours.features.calendar.model.CalendarState
import ke.co.tulivuapps.hobbyhorsetours.features.component.MAX_PEOPLE
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.toJson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

/**
 * Created by brendenozie on 30.03.2023
 */

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class BookNowViewModel @Inject constructor(
    private val bookingsRepository: BookingsRepository,
    savedStateHandle: SavedStateHandle,
    private val dataStoreOperation: DataStoreOperation
) : BaseViewModel<BookNowViewState, BookNowViewEvent>() {


    private val _onLoginCompleted = MutableStateFlow(false)
    val onLoginCompleted: StateFlow<Boolean> = _onLoginCompleted

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _user = MutableStateFlow<DataState<UserInfoResponse>?>(null)
    val user: MutableStateFlow<DataState<UserInfoResponse>?> = _user

    private val _userDetails = MutableStateFlow<UserInfoResponse?>(null)
    val userDetails: MutableStateFlow<UserInfoResponse?> = _userDetails

    private val _signedIn = MutableStateFlow(false)

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    var selectedLogin :Boolean =  false

    var selectedEmail: String? = null

    val calendarState = CalendarState()

    init {
        savedStateHandle.get<String>("bookingNow")?.let {
            setState { currentState.copy(isLoading = false, data = Result.create(it)) }
        } ?: kotlin.run {
            setEvent(BookNowViewEvent.SnackBarError("Something went wrong"))
        }

        viewModelScope.launch {
            selectedLogin =  dataStoreOperation.readOnLoginState().first()
            selectedEmail =  dataStoreOperation.readEmailState().first()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onDaySelected(daySelected: LocalDate) {
        viewModelScope.launch {
            calendarState.setSelectedDay(daySelected)
        }
    }

    fun updatePeople(people: Int) {
        viewModelScope.launch {
            if (people > MAX_PEOPLE) {
                //_suggestedDestinations.value = emptyList()
            } else {
//                val newDestinations = withContext(defaultDispatcher) {
//                    destinationsRepository.destinations
//                        .shuffled(Random(people * (1..100).shuffled().first()))
//                }
//                _suggestedDestinations.value = newDestinations
            }
        }
    }

    fun updateChildren(children: Int) {
        viewModelScope.launch {
            if (children> MAX_PEOPLE) {
                //_suggestedDestinations.value = emptyList()
            } else {
//                val newDestinations = withContext(defaultDispatcher) {
//                    destinationsRepository.destinations
//                        .shuffled(Random(people * (1..100).shuffled().first()))
//                }
//                _suggestedDestinations.value = newDestinations
            }
        }
    }

    fun updateRooms(rooms: Int) {
        viewModelScope.launch {
            if (rooms> MAX_PEOPLE) {
                //_suggestedDestinations.value = emptyList()
            } else {
//                val newDestinations = withContext(defaultDispatcher) {
//                    destinationsRepository.destinations
//                        .shuffled(Random(people * (1..100).shuffled().first()))
//                }
//                _suggestedDestinations.value = newDestinations
            }
        }
    }


    fun BookNow() = viewModelScope.launch {

        try {

//            val email = runBlocking(this.coroutineContext) { selectedEmail.first() }

            val startdate = calendarState.calendarUiState.value.selectedStartDate.toString()
            val enddate =calendarState.calendarUiState.value.selectedStartDate.toString()

            currentState.data?.let { itHotel ->
                BookingInfoResponse(
                    id = null,
                    createdAt = "",
                    sessionId = "123456789",
                    hotellId = itHotel.id,
                    description = itHotel.description,
                    startDate = startdate,
                    endDate = enddate,
                    img = itHotel.img.toJson(),
                    lat=0.0f,
                    location=itHotel.location,
                    long=0.0f,
                    price="1000",
                    status = "active pending",
                    star=0.0f,
                    title=itHotel.title,
                    total=0.0f,
                    userEmail=selectedEmail ?: "",
                    cityId=itHotel.cityId
                )
            }?.let { itInfoResponse ->
//                setEvent(BookNowViewEvent.SnackBarError("$itInfoResponse"))
                bookingsRepository.bookHotel(
                    itInfoResponse
                ).collect{
                    when (it) {
                        is DataState.Success -> {
                                setState { currentState.copy(bookingResponse = it.data, isLoading = false) }
                        }

                        is DataState.Error -> {
                                setState { currentState.copy(isLoading = false) }
                                setEvent(BookNowViewEvent.SnackBarError(it.apiError?.message))
                        }

                        is DataState.Loading -> {
                                setState { currentState.copy(isLoading = true) }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            setEvent(BookNowViewEvent.SnackBarError(e.message))//.localizedMessage))
        } finally {
            setState { currentState.copy(isLoading = false) }
        }
    }


    private fun updateBooking(dto: BookingDto) = viewModelScope.launch {
//        val params = UpdateBookingUseCase.Params(dto)
//        call(updateHotelFavoriteUseCase(params))
    }

    override fun createInitialState() = BookNowViewState()

    override fun onTriggerEvent(event: BookNowViewEvent) {
        viewModelScope.launch {
            when (event) {
                is BookNowViewEvent.UpdateBooking -> updateBooking(event.dto)
                is BookNowViewEvent.SnackBarError -> {}
                else -> {}
            }
        }
    }
}

sealed class BookNowViewEvent : IViewEvent {
    class UpdateBooking(val dto: BookingDto) : BookNowViewEvent()
    class SnackBarError(val message: String?) : BookNowViewEvent()
}