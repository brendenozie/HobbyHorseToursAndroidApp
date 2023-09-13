package ke.co.tulivuapps.hobbyhorsetours.features.screen.destinationsdetail

import androidx.lifecycle.SavedStateHandle
import ke.co.tulivuapps.hobbyhorsetours.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.destinationsdetail.DestinationsDetailViewState
import javax.inject.Inject

/**
 * Created by brendenozie on 13.03.2023
 */
@HiltViewModel
class DestinationsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DestinationsDetailViewState, DestinationsDetailViewEvent>() {

    init {
        savedStateHandle.get<String>("destinationDetail")?.let {
            setState { currentState.copy(isLoading = false, data = Result.create(it)) }
        } ?: kotlin.run {
           setEvent(DestinationsDetailViewEvent.SnackBarError("Something went wrong"))
        }
    }

    override fun createInitialState() = DestinationsDetailViewState()
    override fun onTriggerEvent(event: DestinationsDetailViewEvent) {}
}

sealed class DestinationsDetailViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : DestinationsDetailViewEvent()
}