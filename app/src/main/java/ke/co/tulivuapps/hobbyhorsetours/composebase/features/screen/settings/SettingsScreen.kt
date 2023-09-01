package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ke.co.tulivuapps.hobbyhorsetours.composebase.R
import androidx.hilt.navigation.compose.hiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.composebase.BuildConfig
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursText
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursTopBar

/**
 * Created by brendenozie on 22.03.2023
 */

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()

    HobbyHorseToursScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            HobbyHorseToursTopBar(
                text = stringResource(id = R.string.settings_screen_title),
                elevation = 10.dp,
            )
        },
        content = {
            Content(
                isDark = viewState.isDark,
                onTriggerEvent = {
                    viewModel.onTriggerEvent(it)
                }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )

}

@Composable
private fun Content(
    isDark: Boolean = false,
    onTriggerEvent: (SettingsViewEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .border(
                    border = BorderStroke(width = 1.dp, color = Color.LightGray),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                HobbyHorseToursText(
                    text = stringResource(id = R.string.settings_dark_theme),
                    style = MaterialTheme.typography.body2
                )

                Switch(
                    checked = isDark,
                    onCheckedChange = {
                        onTriggerEvent.invoke(SettingsViewEvent.OnChangeTheme)
                    })
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                HobbyHorseToursText(
                    text = stringResource(id = R.string.settings_screen_app_version_title),
                    style = MaterialTheme.typography.body2
                )

                HobbyHorseToursText(
                    text = BuildConfig.VERSION_NAME,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}
