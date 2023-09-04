package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.hotelsdetail

import androidx.lifecycle.SavedStateHandle
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.hotelsdetail.HotelsDetailViewState
import javax.inject.Inject

/**
 * Created by brendenozie on 13.03.2023
 */
@HiltViewModel
class HotelsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<HotelsDetailViewState, HotelsDetailViewEvent>() {

    init {
        savedStateHandle.get<String>("hotelDetail")?.let {
            setState { currentState.copy(isLoading = false, data = Result.create(it)) }
        } ?: kotlin.run {
           setEvent(HotelsDetailViewEvent.SnackBarError("Something went wrong"))
        }
    }

    override fun createInitialState() = HotelsDetailViewState()
    override fun onTriggerEvent(event: HotelsDetailViewEvent) {}
}

sealed class HotelsDetailViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : HotelsDetailViewEvent()
}