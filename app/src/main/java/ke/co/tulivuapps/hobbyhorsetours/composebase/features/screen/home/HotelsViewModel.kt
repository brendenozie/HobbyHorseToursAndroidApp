package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.hotels.GetHotelsUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.usecase.hotels.UpdateHotelFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.hotels.HotelsViewState
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by brendenozie on 13.03.2023
 */

@HiltViewModel
class HotelsViewModel @Inject constructor(
    private val getHotelsUseCase: GetHotelsUseCase,
    private val updateHotelFavoriteUseCase: UpdateHotelFavoriteUseCase
) : BaseViewModel<HotelsViewState, HotelsViewEvent>() {

    private val config = PagingConfig(pageSize = 20)

    init {
        getAllHotels()
    }

    private fun getAllHotels() {
        viewModelScope.launch {
            setState { currentState.copy(isLoading = true) }
            val params = GetHotelsUseCase.Params(config, hashMapOf())
            val pagedFlow = getHotelsUseCase(params).cachedIn(scope = viewModelScope)
            delay(1000)
            setState { currentState.copy(isLoading = false, pagedData = pagedFlow) }
        }
    }

    private fun updateHotelFavorite(dto: HotelDto) = viewModelScope.launch {
        val params = UpdateHotelFavoriteUseCase.Params(dto)
        call(updateHotelFavoriteUseCase(params))
    }


    override fun createInitialState() = HotelsViewState()

    override fun onTriggerEvent(event: HotelsViewEvent) {
        viewModelScope.launch {
            when (event) {
                is HotelsViewEvent.UpdateHotelFavorite -> updateHotelFavorite(event.dto)
            }
        }
    }
}

sealed class HotelsViewEvent : IViewEvent {
    class UpdateHotelFavorite(val dto: HotelDto) : HotelsViewEvent()
}