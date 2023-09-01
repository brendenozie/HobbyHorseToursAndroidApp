package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.splash

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewState

/**
 * Created by brendenozie on 12.03.2023
 */

@Stable
data class SplashViewState(
    val isLoading: Boolean = false
) : IViewState