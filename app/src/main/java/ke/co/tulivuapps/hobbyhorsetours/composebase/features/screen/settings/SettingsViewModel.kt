package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.settings

import androidx.lifecycle.viewModelScope
import ke.co.tulivuapps.hobbyhorsetours.composebase.HobbyHorseToursApp
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.settings.SettingsViewState
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by brendenozie on 22.03.2023
 */

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val application: HobbyHorseToursApp
) : BaseViewModel<SettingsViewState, SettingsViewEvent>() {

    init {
        setState { currentState.copy(isDark = application.isDark.value) }
    }

   private fun onChangeTheme() {
        viewModelScope.launch {
            application.toggleTheme()
            setState { currentState.copy(isDark = application.isDark.value) }
        }
    }

    override fun onTriggerEvent(event: SettingsViewEvent) {
        viewModelScope.launch {
            when (event) {
                is SettingsViewEvent.OnChangeTheme -> onChangeTheme()
            }
        }
    }

    override fun createInitialState() = SettingsViewState()
}

sealed class SettingsViewEvent : IViewEvent {
    object OnChangeTheme : SettingsViewEvent()
}
