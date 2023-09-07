package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.destinations.GetDestinationsUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.destinations.UpdateDestinationFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.destinations.DestinationsViewState
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by brendenozie on 13.03.2023
 */

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val updateCitiesFavoriteUseCase: UpdateCitiesFavoriteUseCase
) : BaseViewModel<CitiesViewState, CitiesViewEvent>() {

    private val config = PagingConfig(pageSize = 20)

    init {
        getAllDestinations()
    }

    private fun getAllDestinations() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            val params = getCitiesUseCase.Params(config, hashMapOf())
            val pagedFlow = getCitiesUseCase(params).cachedIn(scope = viewModelScope)
            delay(1000)
            setState { currentState.copy(isLoading = false, pagedData = pagedFlow) }
        }
    }

    private fun updateDestinationFavorite(dto: DestinationDto) = viewModelScope.launch {
        val params = updateCitiesFavoriteUseCase.Params(dto)
        call(updateCitiesFavoriteUseCase(params))
    }


    override fun createInitialState() = DestinationsViewState()

    override fun onTriggerEvent(event: DestinationsViewEvent) {
        viewModelScope.launch {
            when (event) {
                is CitiesViewEvent.UpdateCitiesFavorite -> updateDestinationFavorite(event.dto)
            }
        }
    }
}

sealed class CitiesViewEvent : IViewEvent {
    class UpdateCitiesFavorite(val dto: CitiesDto) : CitiesViewEvent()
}