package ke.co.tulivuapps.hobbyhorsetours.features.screen.travelstyles

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.travelstyle.GetTravelStyleUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.travelstyle.UpdateTravelStyleFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.travelstyle.TravelStyleViewState
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by brendenozie on 13.03.2023
 */

@HiltViewModel
class TravelStyleViewModel @Inject constructor(
    private val getTravelStyleUseCase: GetTravelStyleUseCase,
    private val updateTravelStyleFavoriteUseCase: UpdateTravelStyleFavoriteUseCase
) : BaseViewModel<TravelStyleViewState, TravelStyleViewEvent>() {

    private val config = PagingConfig(pageSize = 20)

    init {
        getAllTravelStyle()
    }

    private fun getAllTravelStyle() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            val params = GetTravelStyleUseCase.Params(config, hashMapOf())
            val pagedFlow = getTravelStyleUseCase(params).cachedIn(scope = viewModelScope)
            delay(1000)
            setState { currentState.copy(isLoading = false, pagedData = pagedFlow) }
        }
    }

    private fun updateTravelStyleFavorite(dto: TravelStyleDto) = viewModelScope.launch {
        val params = UpdateTravelStyleFavoriteUseCase.Params(dto)
        call(updateTravelStyleFavoriteUseCase(params))
    }


    override fun createInitialState() = TravelStyleViewState()

    override fun onTriggerEvent(event: TravelStyleViewEvent) {
        viewModelScope.launch {
            when (event) {
                is TravelStyleViewEvent.UpdateTravelStyleFavorite -> updateTravelStyleFavorite(event.dto)
            }
        }
    }
}

sealed class TravelStyleViewEvent : IViewEvent {
    class UpdateTravelStyleFavorite(val dto: TravelStyleDto) : TravelStyleViewEvent()
}