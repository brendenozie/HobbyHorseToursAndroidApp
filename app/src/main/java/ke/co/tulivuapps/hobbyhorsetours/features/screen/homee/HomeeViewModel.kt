package ke.co.tulivuapps.hobbyhorsetours.features.screen.homee

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.local.DataStoreOperation
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.city.GetCityUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.city.UpdateCityFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.destinations.GetDestinationsUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.destinations.UpdateDestinationFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.hotels.GetHotelsUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.hotels.UpdateHotelFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.travelstyle.GetTravelStyleUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.travelstyle.UpdateTravelStyleFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.home.HomeViewState
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeeViewModel @Inject constructor(
    private val getDestinationsUseCase: GetDestinationsUseCase,
    private val updateDestinationFavoriteUseCase: UpdateDestinationFavoriteUseCase,
    private val getcityUseCase: GetCityUseCase,
    private val updateCityFavoriteUseCase: UpdateCityFavoriteUseCase,
    private val getTravelStyleUseCase: GetTravelStyleUseCase,
    private val updateTravelStyleFavoriteUseCase: UpdateTravelStyleFavoriteUseCase,
    private val getHotelsUseCase: GetHotelsUseCase,
    private val updateHotelFavoriteUseCase: UpdateHotelFavoriteUseCase,
    private val dataStoreOperation: DataStoreOperation
) : BaseViewModel<HomeViewState, HomeViewEvent>() {

    private val config = PagingConfig(pageSize = 10)

    val selectedLogin =  dataStoreOperation.readOnLoginState().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            false
        )

    val selectedUsername =  dataStoreOperation.readNameState().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ""
    )

    private val paramsHotel = GetHotelsUseCase.Params(config, hashMapOf())
    private val paramsCity = GetCityUseCase.Params(config, hashMapOf())
    private val paramsTravelStyles = GetTravelStyleUseCase.Params(config, hashMapOf())
    private val paramsDestination = GetDestinationsUseCase.Params(config, hashMapOf())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadcontent()
        }
    }

    fun loadcontent(){

        setState { currentState.copy(isLoading = true)}

        viewModelScope.launch(Dispatchers.IO) {
            //             Trigger repository requests in parallel
            val cityDeferred = async { getcityUseCase(paramsCity).cachedIn(scope = viewModelScope) }
            val travelStyleDeferred =
                async { getTravelStyleUseCase(paramsTravelStyles).cachedIn(scope = viewModelScope) }
            val destinationDeferred =
                async { getDestinationsUseCase(paramsDestination).cachedIn(scope = viewModelScope) }
            val hotelDeferred =
                async { getHotelsUseCase(paramsHotel).cachedIn(scope = viewModelScope) }

            // Wait for all requests to finish
            val city = cityDeferred.await()
            val travelstyle = travelStyleDeferred.await()
            val destinations = destinationDeferred.await()
            val hotels = hotelDeferred.await()

            setState {
                currentState.copy(
                    isLoading = false,
                    pagedCityData = city,
                    pagedTravelStyleData = travelstyle,
                    pagedDestinationData = destinations,
                    pagedHotelData = hotels
                )
            }
        }
    }

    private fun getAllCity() {
        viewModelScope.launch(Dispatchers.IO) {
            setState { currentState.copy(isLoading = true) }
            val params = GetCityUseCase.Params(config, hashMapOf())
            val pagedFlow = getcityUseCase(params).cachedIn(scope = viewModelScope)
            setState { currentState.copy(isLoading = false, pagedCityData = pagedFlow) }
        }
    }

    private fun getAllTravelStyle() {
        viewModelScope.launch(Dispatchers.IO) {
            setState { currentState.copy(isLoading = true) }
            val params = GetTravelStyleUseCase.Params(config, hashMapOf())
            val pagedFlow = getTravelStyleUseCase(params).cachedIn(scope = viewModelScope)
//            delay(1000)
//            setState { currentState.copy(isLoading = false, pagedTravelStyleData = pagedFlow) }
        }
    }

    private fun getAllDestinations() {
        viewModelScope.launch(Dispatchers.IO) {
            setState { currentState.copy(isLoading = true) }
            val params = GetDestinationsUseCase.Params(config, hashMapOf())
            val pagedFlow = getDestinationsUseCase(params).cachedIn(scope = viewModelScope)
//            delay(1000)
//            setState { currentState.copy(isLoading = false, pagedDestinationData = pagedFlow) }
        }
    }

    private fun getAllHotels() {
        viewModelScope.launch(Dispatchers.IO) {
            setState { currentState.copy(isLoading = true) }
            val params = GetHotelsUseCase.Params(config, hashMapOf())
            val pagedFlow = getHotelsUseCase(params).cachedIn(scope = viewModelScope)
//            delay(1000)
//            setState { currentState.copy(isLoading = false, pagedHotelData = pagedFlow) }
        }
    }

    fun signOut() = Firebase.auth.signOut()

    override fun createInitialState() = HomeViewState()

    override fun onTriggerEvent(event: HomeViewEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeViewEvent.UpdateHomeFavorite -> {}
                is HomeViewEvent.SnackBarError -> {}
                else -> {}
            }
        }
    }
}

sealed class HomeViewEvent : IViewEvent {
    class UpdateHomeFavorite(val dto: CityDto) : HomeViewEvent()
    class SnackBarError(val message: String?) : HomeViewEvent()
}