package ke.co.tulivuapps.hobbyhorsetours.features.screen.popular

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.data.model.popular.ResultPopular
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursEpisodesShimmer
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursPopularCard
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursTopBar
import kotlinx.coroutines.launch

/**
 * Created by brendenozie on 19.03.2023
 */

@Composable
fun PopularScreen(
    viewModel: PopularViewModel
) {
    val scaffoldState = rememberScaffoldState()
    val viewState = viewModel.uiState.collectAsState().value

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is PopularViewEvent.SnackBarError -> {
                        scaffoldState.snackbarHostState.showSnackbar(it.message.orEmpty())
                    }

                    else -> {}
                }
            }
        }
    }


    HobbyHorseToursScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            HobbyHorseToursTopBar(
                text = stringResource(id = R.string.popular_screen_title),
                actions = {
                    IconButton(onClick = {
                    }) {
                    }
                },
                elevation = 10.dp,
            )
        },
        content = {
            Content(
                isLoading = viewState.isLoading,
                data = viewState.data,
            )
        }
    )
}

@Composable
private fun Content(
    isLoading: Boolean,
    data: List<ResultPopular>?,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (isLoading) {
                items(10) {
                    HobbyHorseToursEpisodesShimmer()
                }
            } else {
                items(items = data ?: listOf()) { item ->
                    HobbyHorseToursPopularCard(
                        name = item.document.title.orEmpty(),
                        date = item.count.toString(),//.orEmpty(),
                        episode = item.document.star.orEmpty(),
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "Light Mode"
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark Mode"
)
@Composable
fun DetailContentItemViewPreview() {
    PopularScreen(viewModel = hiltViewModel())
}