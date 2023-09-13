package ke.co.tulivuapps.hobbyhorsetours.features.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Created by brendenozie on 12.03.2023
 */

@Composable
fun HobbyHorseToursTopBar(
    modifier: Modifier = Modifier,
    text: String,
    title: @Composable () -> Unit = {
        HobbyHorseToursText(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.secondary,
            style = MaterialTheme.typography.subtitle1
        )
    },
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.background,
    elevation: Dp = 0.dp
) {
    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = elevation
    )
}

@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    HobbyHorseToursTopBar(
        modifier = Modifier,
        text = "Value",
        title = {
            HobbyHorseToursText(
                text = "Title",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle1
            )
        },
    )
}
