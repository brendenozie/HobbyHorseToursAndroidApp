package ke.co.tulivuapps.hobbyhorsetours.features.screen.cities

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.city.GetCityUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.usecase.city.UpdateCityFavoriteUseCase
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.city.CityViewState
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by brendenozie on 13.03.2023
 */

@HiltViewModel
class CityViewModel @Inject constructor(
    private val getcityUseCase: GetCityUseCase,
    private val updateCityFavoriteUseCase: UpdateCityFavoriteUseCase
) : BaseViewModel<CityViewState, CityViewEvent>() {

    private val config = PagingConfig(pageSize = 2)
    init {
        //getAllCity()
    }

    private fun getAllCity() {
        viewModelScope.launch(Dispatchers.IO) {
            setState { currentState.copy(isLoading = true) }
            val params = GetCityUseCase.Params(config, hashMapOf())
            val pagedFlow = getcityUseCase(params).cachedIn(scope = viewModelScope)
            setState { currentState.copy(isLoading = false, pagedData = pagedFlow) }
        }
    }

    private fun updateCityFavorite(dto: CityDto) = viewModelScope.launch {
        val params = UpdateCityFavoriteUseCase.Params(dto)
        call(updateCityFavoriteUseCase(params))
    }


    override fun createInitialState() = CityViewState()

    override fun onTriggerEvent(event: CityViewEvent) {
        viewModelScope.launch {
            when (event) {
                is CityViewEvent.UpdateCityFavorite -> updateCityFavorite(event.dto)
            }
        }
    }
}

sealed class CityViewEvent : IViewEvent {
    class UpdateCityFavorite(val dto: CityDto) : CityViewEvent()
}