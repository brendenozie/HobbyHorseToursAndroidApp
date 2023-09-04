package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.CharacterDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.hotels.GetHotelsUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.favorite.UpdateFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.characters.CharactersViewState
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.destinations.GetDestinationsUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.destinations.UpdateDestinationFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.hotels.UpdateHotelFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.destinations.DestinationsViewState
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.hotels.HotelsViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by brendenozie on 13.03.2023
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHotelsUseCase: GetHotelsUseCase,
    private val getDestinationsUseCase: GetDestinationsUseCase,
    private val updateDestinationFavoriteUseCase: UpdateDestinationFavoriteUseCase
) : BaseViewModel<DestinationsViewState, DestinationsViewEvent>() {

    private val config = PagingConfig(pageSize = 20)

    init {
        getAllDestinations()
    }

    private fun getAllDestinations() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            val params = GetDestinationsUseCase.Params(config, hashMapOf())
            val pagedFlow = getDestinationsUseCase(params).cachedIn(scope = viewModelScope)
            delay(1000)
            setState { currentState.copy(isLoading = false, pagedData = pagedFlow) }
        }
    }

    private fun updateHotelFavorite(dto: HotelDto) = viewModelScope.launch {
//        val params = UpdateHotelFavoriteUseCase.Params(dto)
//        call(updateHotelFavoriteUseCase(params))
    }

    private fun updateDestinationFavorite(dto: DestinationDto) = viewModelScope.launch {
        val params = UpdateDestinationFavoriteUseCase.Params(dto)
        call(updateDestinationFavoriteUseCase(params))
    }


    override fun createInitialState() = DestinationsViewState()

    override fun onTriggerEvent(event: DestinationsViewEvent) {
        viewModelScope.launch {
            when (event) {
                is DestinationsViewEvent.UpdateDestinationFavorite -> updateDestinationFavorite(event.dto)
            }
        }
    }
}

sealed class CharactersViewEvent : IViewEvent {
    class UpdateFavorite(val dto: CharacterDto) : CharactersViewEvent()
}

sealed class HotelsViewEvent : IViewEvent {
    class UpdateHotelFavorite(val dto: HotelDto) : HotelsViewEvent()
}

sealed class DestinationsViewEvent : IViewEvent {
    class UpdateDestinationFavorite(val dto: DestinationDto) : DestinationsViewEvent()
}