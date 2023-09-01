package ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.settings

import androidx.compose.runtime.Stable
import ke.co.tulivuapps.hobbyhorsetours.composebase.domain.viewstate.IViewState

/**
 * Created by brendenozie on 22.03.2023
 */

@Stable
data class SettingsViewState(
    val isDark: Boolean = false,
) : IViewState