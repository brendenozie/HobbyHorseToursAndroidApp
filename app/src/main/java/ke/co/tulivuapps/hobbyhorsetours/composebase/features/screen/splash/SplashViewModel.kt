package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.splash

import androidx.lifecycle.viewModelScope
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewEvent
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.splash.SplashViewState
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by brendenozie on 12.03.2023
 */

@HiltViewModel
class SplashViewModel @Inject constructor(
) : BaseViewModel<SplashViewState, SplashViewEvent>() {

    init {
        viewModelScope.launch {
            delay(2000)
            setEvent(SplashViewEvent.DirectToDashBoard)
        }
    }

    override fun createInitialState() = SplashViewState()
    override fun onTriggerEvent(event: SplashViewEvent) {}
}

sealed class SplashViewEvent : IViewEvent {
    object DirectToDashBoard : SplashViewEvent()
}