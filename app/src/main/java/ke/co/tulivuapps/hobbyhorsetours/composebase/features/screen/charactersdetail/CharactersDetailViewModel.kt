package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.charactersdetail

import androidx.lifecycle.SavedStateHandle
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.charactersdetail.CharactersDetailViewState
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by brendenozie on 13.03.2023
 */
@HiltViewModel
class CharactersDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CharactersDetailViewState, CharactersDetailViewEvent>() {

    init {
        savedStateHandle.get<String>("characterDetail")?.let {
            setState { currentState.copy(isLoading = false, data = Result.create(it)) }
        } ?: kotlin.run {
           setEvent(CharactersDetailViewEvent.SnackBarError("Something went wrong"))
        }
    }

    override fun createInitialState() = CharactersDetailViewState()
    override fun onTriggerEvent(event: CharactersDetailViewEvent) {}
}

sealed class CharactersDetailViewEvent : IViewEvent {
    class SnackBarError(val message: String?) : CharactersDetailViewEvent()
}