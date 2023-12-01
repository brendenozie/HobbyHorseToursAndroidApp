package ke.co.tulivuapps.hobbyhorsetours.features.screen.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.Constants
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.city.GetCityUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.destinations.GetDestinationsFilterUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.destinations.UpdateDestinationFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.hotels.GetHotelsFilterUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.hotels.UpdateHotelFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.travelstyle.GetTravelStyleUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.search.SearchViewState
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by brendenozie on 9.04.2023
 */

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getHotelsFilterUseCase: GetHotelsFilterUseCase,
    private val getcityUseCase: GetCityUseCase,
    private val getTravelStyleUseCase: GetTravelStyleUseCase,
    private val getDestinationsFilterUseCase: GetDestinationsFilterUseCase,
    private val updateHotelFavoriteUseCase: UpdateHotelFavoriteUseCase,
    private val updateDestinationFavoriteUseCase: UpdateDestinationFavoriteUseCase
) : BaseViewModel<SearchViewState, SearchViewEvent>() {

    private val config = PagingConfig(pageSize = 20)

    init {
        viewModelScope.launch(Dispatchers.IO) {

            savedStateHandle.get<String>("travelstyle")?.let {
                setState { currentState.copy(isLoading = false, selectedTravelStyleData = TravelStyleDto.create(it)) }
            } ?: kotlin.run {
                setEvent(SearchViewEvent.SnackBarError("Something went wrong"))
            }

            savedStateHandle.get<String>("city")?.let {
                setState { currentState.copy(isLoading = false, selectedCityData = CityDto.create(it)) }
            } ?: kotlin.run {
                setEvent(SearchViewEvent.SnackBarError("Something went wrong"))
            }

            val paramsCity = GetCityUseCase.Params(config, hashMapOf())
            val paramsTravelStyle = GetTravelStyleUseCase.Params(config, hashMapOf())

            // Trigger repository requests in parallel
            val pagedCityFlow = async { getcityUseCase(paramsCity).cachedIn(scope = viewModelScope) }
            val pagedTravelStyleFlow = async { getTravelStyleUseCase(paramsTravelStyle).cachedIn(scope = viewModelScope) }

            // Wait for all requests to finish
            val cities = pagedCityFlow.await()
            val travelStyles = pagedTravelStyleFlow.await()

            setState {
                currentState.copy(
                    isLoading = false,
                    pagedCityData = cities,
                    pagedTravelStyleData = travelStyles
                )
            }

            onSearch(currentState)
        }
    }

    override fun onTriggerEvent(event: SearchViewEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                is SearchViewEvent.NewSearchEvent -> {
                    onSearch(currentState)
                }
                is SearchViewEvent.UpdateHotelFavorite -> {
                    updateHotelFavorite(event.dto)
                }
                is SearchViewEvent.UpdateDestinationFavorite -> {
                    updateDestinationFavorite(event.dto)
                }
                is SearchViewEvent.SnackBarError -> {}
            }
        }
    }

    private fun onSearch(viewState: SearchViewState) {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }

            val queryData = HashMap<String, String>()
            viewState.searchText?.let { queryData[Constants.PARAM_NAME] = it }
            viewState.selectedCityData.let {
                if (it != null) {
                    queryData[Constants.PARAM_CITY] = it.id ?: ""
                }
            }
            viewState.selectedTravelStyleData.let {
                if (it != null) {
                    queryData[Constants.PARAM_TRAVEL_STYLE] = it.id ?: ""
                }
            }

            val paramsHotel = GetHotelsFilterUseCase.Params(config, queryData)
            val paramsDestination = GetDestinationsFilterUseCase.Params(config, queryData)

            // Trigger repository requests in parallel
            val pagedHotelFlow = async { getHotelsFilterUseCase(paramsHotel).cachedIn(scope = viewModelScope) }
            val pagedDestinationFlow = async { getDestinationsFilterUseCase(paramsDestination).cachedIn(scope = viewModelScope) }

            // Wait for all requests to finish
            val hotels = pagedHotelFlow.await()
            val destinations = pagedDestinationFlow.await()

            setState {
                currentState.copy(
                    isLoading = false,
                    pagedDestinationData = destinations,
                    pagedHotelData = hotels
                )
            }
        }
    }

    private fun updateHotelFavorite(dto: HotelDto) = viewModelScope.launch {
        val params = UpdateHotelFavoriteUseCase.Params(dto)
        call(updateHotelFavoriteUseCase(params))
    }
    private fun updateDestinationFavorite(dto: DestinationDto) = viewModelScope.launch {
        val params = UpdateDestinationFavoriteUseCase.Params(dto)
        call(updateDestinationFavoriteUseCase(params))
    }

    fun searchText(value: String?) {
        setState { currentState.copy(searchText = value) }
    }

    fun selectCity(value: CityDto) {
        if(currentState.selectedCityData !=null && currentState.selectedCityData!!.cityName == value.cityName){
            setState { currentState.copy(selectedCityData = null) }
        }else {
            setState { currentState.copy(selectedCityData = value) }
        }
    }

    fun selectTravelStyle(value: TravelStyleDto) {
        if(currentState.selectedTravelStyleData !=null && currentState.selectedTravelStyleData!!.styleName == value.styleName){
            setState { currentState.copy(selectedTravelStyleData = null) }
        }else {
            setState { currentState.copy(selectedTravelStyleData = value) }
        }
        //setState { currentState.copy(pagedTravelStyleData = currentState.pagedTravelStyleData.map { it.copy(selected = it.name == value && it.selected.not()) }) }
    }

    override fun createInitialState() = SearchViewState()
}

sealed class SearchViewEvent : IViewEvent {
    object NewSearchEvent : SearchViewEvent()
    class UpdateHotelFavorite(val dto: HotelDto) : SearchViewEvent()
    class UpdateDestinationFavorite(val dto: DestinationDto) : SearchViewEvent()
    class SnackBarError(val message: String?) : SearchViewEvent()
}
