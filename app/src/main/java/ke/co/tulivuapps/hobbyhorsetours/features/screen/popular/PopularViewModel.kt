package ke.co.tulivuapps.hobbyhorsetours.features.screen.popular

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.data.remote.utils.DataState
import ke.co.tulivuapps.hobbyhorsetours.domain.repository.PopularRepository
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.popular.PopularViewState
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by brendenozie on 19.03.2023
 */

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val episodesRepository: PopularRepository
) : BaseViewModel<PopularViewState, PopularViewEvent>() {

    init {
        getAllPopular()
    }

    private fun getAllPopular() {
        viewModelScope.launch(Dispatchers.IO) {
            setState { currentState.copy(isLoading = true) }
            delay(2000)
            episodesRepository.getAllPopular().collect {
                when (it) {
                    is DataState.Success -> {
                        setState { currentState.copy(data = it.data.results, isLoading = false) }
                    }
                    is DataState.Error -> {
                        setState { currentState.copy(isLoading = false) }
                        setEvent(PopularViewEvent.SnackBarError(it.apiError?.message))
                    }
                    is DataState.Loading -> {
                        setState { currentState.copy(isLoading = true) }
                    }
                }
            }

        }
    }

    override fun createInitialState() = PopularViewState()
    override fun onTriggerEvent(event: PopularViewEvent) {}
}

sealed class PopularViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : PopularViewEvent()
}