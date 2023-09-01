package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.characters

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import ke.co.tulivuapps.hobbyhorsetours.composebase.R
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.Status
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.CharacterDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursCharacterShimmer
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursCharactersCard
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursTopBar
import ke.co.tulivuapps.hobbyhorsetours.composebase.utils.Utility.rememberFlowWithLifecycle
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 13.03.2023
 */

@Composable
fun CharactersScreen(
    viewModel: CharactersViewModel,
    navigateToDetail: (CharacterDto?) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val viewState = viewModel.uiState.collectAsState().value

    HobbyHorseToursScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            HobbyHorseToursTopBar(
                text = stringResource(id = R.string.characters_screen_title),
                elevation = 10.dp,
            )
        },
        content = {
            Content(
                isLoading = viewState.isLoading,
                pagedData = viewState.pagedData,
                onTriggerEvent = {
                  viewModel.onTriggerEvent(it)
                },
                clickDetail = {
                    navigateToDetail.invoke(it)
                }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
private fun Content(
    isLoading :Boolean = false,
    pagedData: Flow<PagingData<CharacterDto>>? = null,
    onTriggerEvent: (CharactersViewEvent) -> Unit,
    clickDetail: (CharacterDto?) -> Unit
) {
    var pagingItems: LazyPagingItems<CharacterDto>? = null
    pagedData?.let {
        pagingItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

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
                    HobbyHorseToursCharacterShimmer()
                }
            } else if (pagedData != null && pagingItems != null) {
                items(items = pagingItems!!) { item ->
                    HobbyHorseToursCharactersCard(
                        status = item?.status ?: Status.Unknown,
                        detailClick = {
                            clickDetail.invoke(item)
                        },
                        dto = item,
                        onTriggerEvent = {
                            onTriggerEvent.invoke(CharactersViewEvent.UpdateFavorite(it))
                        }
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
    CharactersScreen(viewModel = hiltViewModel(), navigateToDetail = {})
}