package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.Status
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursCharacterShimmer
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursDestinationsCard
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.HomeCategoryView
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.SearchBox
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.SlidingBanner
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.component.TopBar
import ke.co.tulivuapps.hobbyhorsetours.composebase.utils.Utility.rememberFlowWithLifecycle
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 13.03.2023
 */

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToDestination: (DestinationDto?) -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val viewState = viewModel.uiState.collectAsState().value

    HobbyHorseToursScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(onNavigationIconClick = {    })
        },
        content = {
            Content(
                isLoading = viewState.isLoading,
                pagedData = viewState.pagedData,
                onTriggerEvent = {
                  viewModel.onTriggerEvent(it)
                },
                clickDetail = {
                    navigateToDestination.invoke(it)
                }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
private fun Content(
    isLoading :Boolean = false,
    pagedData: Flow<PagingData<DestinationDto>>? = null,
    onTriggerEvent: (DestinationsViewEvent) -> Unit,
    clickDetail: (DestinationDto?) -> Unit
) {
    var pagingItems: LazyPagingItems<DestinationDto>? = null
    val context = LocalContext.current

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
                item(){
                    SearchBox()
                }

                item {
                    SlidingBanner()
                }

                item {
                    HomeCategoryView()
                }

                item {
                    Row(
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Popular",
                            style = TextStyle(
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.subtitle2.copy(color = Color.LightGray)
                        )
                    }

                    LazyRow( modifier = Modifier.fillMaxWidth() ) {
                        items(items = pagingItems!!) { item ->
                            HobbyHorseToursDestinationsCard(
                                status = Status.Alive, //item?.status ?: Status.Unknown,
                                detailClick = {
                                    clickDetail.invoke(item)
                                },
                                dto = item,
                                onTriggerEvent = {
                                    onTriggerEvent.invoke(
                                        DestinationsViewEvent.UpdateDestinationFavorite(
                                            it
                                        )
                                    )
                                }
                            )
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Destinations",
                            style = TextStyle(
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.subtitle2.copy(color = Color.LightGray)
                        )
                    }

                    LazyRow( modifier = Modifier.fillMaxWidth() ) {
                        items(items = pagingItems!!) { item ->
                            HobbyHorseToursDestinationsCard(
                                status = Status.Alive, //item?.status ?: Status.Unknown,
                                detailClick = {
                                    clickDetail.invoke(item)
                                },
                                dto = item,
                                onTriggerEvent = {
                                    onTriggerEvent.invoke(
                                        DestinationsViewEvent.UpdateDestinationFavorite(
                                            it
                                        )
                                    )
                                }
                            )
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Hotels",
                            style = TextStyle(
                                fontSize = 18.sp,
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = "View All",
                            style = MaterialTheme.typography.subtitle2.copy(color = Color.LightGray)
                        )
                    }

                    LazyRow( modifier = Modifier.fillMaxWidth() ) {
                        items(items = pagingItems!!) { item ->
                            HobbyHorseToursDestinationsCard(
                                status = Status.Alive, //item?.status ?: Status.Unknown,
                                detailClick = {
                                    clickDetail.invoke(item)
                                },
                                dto = item,
                                onTriggerEvent = {
                                    onTriggerEvent.invoke(
                                        DestinationsViewEvent.UpdateDestinationFavorite(
                                            it
                                        )
                                    )
                                }
                            )
                        }
                    }
                }



            }else{
                Toast.makeText(context, "Nothing To Show", Toast.LENGTH_SHORT).show()
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
    HomeScreen(viewModel = hiltViewModel()) {}
}