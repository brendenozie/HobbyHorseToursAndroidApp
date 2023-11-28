package ke.co.tulivuapps.hobbyhorsetours.features.screen.home

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import ke.co.tulivuapps.hobbyhorsetours.data.model.Status
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.city.CityViewState
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.destinations.DestinationsViewState
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.hotels.HotelsViewState
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.travelstyle.TravelStyleViewState
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursCharacterShimmer
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursDestinationsCard
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursHotelsCard
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.features.component.RoundedCornerIconButtonCity
import ke.co.tulivuapps.hobbyhorsetours.features.component.RoundedCornerIconButtonTravelStyle
import ke.co.tulivuapps.hobbyhorsetours.features.component.SearchBox
import ke.co.tulivuapps.hobbyhorsetours.features.component.SlidingBanner
import ke.co.tulivuapps.hobbyhorsetours.features.component.TopBar
import ke.co.tulivuapps.hobbyhorsetours.features.component.VerticalGrid
import ke.co.tulivuapps.hobbyhorsetours.features.screen.cities.CityViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.screen.cities.CityViewModel
import ke.co.tulivuapps.hobbyhorsetours.features.screen.destinations.DestinationsViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.screen.destinations.DestinationsViewModel
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.HotelsViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.HotelsViewModel
import ke.co.tulivuapps.hobbyhorsetours.features.screen.travelstyles.TravelStyleViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.screen.travelstyles.TravelStyleViewModel
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.rememberFlowWithLifecycle
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 13.03.2023
 */

@Composable
fun HomeScreen(
    destinationsViewModel: DestinationsViewModel,
    homeViewModel: HomeViewModel,
    travelstyleviewModel: TravelStyleViewModel,
    cityviewModel: CityViewModel,
    hotelsviewModel: HotelsViewModel,
    navigateToSearch: () -> Unit?,
    navigateToCities: () -> Unit?,
    navigateToTravelStyles: () -> Unit?,
    navigateToDestinations: () -> Unit?,
    navigateToHotels: () -> Unit?,
    navigateToDestination: (DestinationDto) -> Unit?,
    navigateToHotel: (HotelDto) -> Unit?
) {

    val scaffoldState = rememberScaffoldState()
    val isLoggedIn by homeViewModel.signedIn.collectAsState()
    val username by homeViewModel.userName.collectAsState()
    val destinationsViewState by  destinationsViewModel.uiState.collectAsState()
    val travelStyleViewState by travelstyleviewModel.uiState.collectAsState()
    val cityViewState by cityviewModel.uiState.collectAsState()
    val hotelsViewState by hotelsviewModel.uiState.collectAsState()

//    LaunchedEffect(key1 = Unit) {
//
//    }

    HobbyHorseToursScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(onNavigationIconClick = {    }, isLoggedIn, username=username )
        },
        content = {
            Content(
                destinationsViewModel = destinationsViewModel,
                destinationsViewState = destinationsViewState,
                travelstyleviewModel = travelstyleviewModel,
                travelStyleViewState = travelStyleViewState,
                cityviewModel = cityviewModel,
                cityViewState = cityViewState,
                hotelsviewModel = hotelsviewModel,
                hotelsViewState = hotelsViewState,
                navigateToSearch = navigateToSearch,
                navigateToCities = navigateToCities,
                navigateToTravelStyles = navigateToTravelStyles,
                navigateToDestinations = navigateToDestinations,
                navigateToHotels = navigateToHotels,
                navigateToDestination = navigateToDestination,
                navigateToHotel = navigateToHotel)
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun Content(
    isLoading:Boolean = false,
    destinationsViewModel: DestinationsViewModel,
    destinationsViewState: DestinationsViewState,
    travelstyleviewModel: TravelStyleViewModel,
    travelStyleViewState:TravelStyleViewState,
    cityviewModel: CityViewModel,
    cityViewState: CityViewState,
    hotelsviewModel: HotelsViewModel,
    hotelsViewState: HotelsViewState,
    navigateToSearch: () -> Unit?,
    navigateToCities: () -> Unit?,
    navigateToTravelStyles: () -> Unit?,
    navigateToDestinations: () -> Unit?,
    navigateToHotels: () -> Unit?,
    navigateToDestination: (DestinationDto) -> Unit?,
    navigateToHotel: (HotelDto) -> Unit?
) {

    var pagingHotelItems: LazyPagingItems<HotelDto>? = null
    hotelsViewState.pagedData?.let {
        pagingHotelItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    AnimatedVisibility(
        true,
        enter = slideIn(
            tween(
                delayMillis = 700,
                easing = LinearOutSlowInEasing,
                durationMillis = 500
            )
        ) { IntOffset(0, 120) },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
        ) {

            if (isLoading) {
                items(10) {
                    HobbyHorseToursCharacterShimmer()
                }
            } else {
                item {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                item {
                    Box(modifier = Modifier.animateEnterExit(
                                enter = slideInVertically(
                                    animationSpec = tween(durationMillis = 5500)
                                ),
                                exit = slideOutVertically(
                                    animationSpec = tween(durationMillis = 5500)
                                )
                            )
                            .fillMaxWidth()
                    ) {
                        SearchBox { navigateToSearch.invoke() }
                    }
                }
                item {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                item {
                    SlidingBanner()
                }
                item {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                item {
                    Row(
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 10.dp,
                            top = 10.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Cities",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.h2,
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp
                        )
                        Text(
                            modifier = Modifier
                                .clickable(onClick = { navigateToCities.invoke() }),
                            text = "View All",
                            style = MaterialTheme.typography.subtitle2.copy(color = Color.Gray)
                        )
                    }
                }
                item {

                    ContentCities(isLoading = cityViewState.isLoading,
                        pagedData = cityViewState.pagedData,
                        onTriggerEvent = {
                            cityviewModel.onTriggerEvent(it)
                        },
                        clickDetail = {
                            //navigateToDestination.invoke(it)
                        })
                }

                item {
                    Row(
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 10.dp,
                            top = 10.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Travel Style",
                            style = MaterialTheme.typography.h2,
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            modifier = Modifier
                                .clickable(onClick = { navigateToTravelStyles.invoke() }),
                            text = "View All",
                            style = MaterialTheme.typography.subtitle2.copy(color = Color.Gray)
                        )
                    }
                }
                item {

                    ContentTravelStyle(isLoading = travelStyleViewState.isLoading,
                        pagedData = travelStyleViewState.pagedData,
                        onTriggerEvent = {
                            travelstyleviewModel.onTriggerEvent(it)
                        },
                        clickDetail = {
                            //navigateToDestination.invoke(it)
                        })

                }

                item {
                    Row(
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 10.dp,
                            top = 10.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Destinations",
                            style = MaterialTheme.typography.h2,
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            modifier = Modifier
                                .clickable(onClick = { navigateToDestinations.invoke() }),
                            text = "View All",
                            style = MaterialTheme.typography.subtitle2.copy(color = Color.Gray)
                        )
                    }
                }

                item {
                    ContentDestinations(isLoading = destinationsViewState.isLoading,
                        pagedData = destinationsViewState.pagedData,
                        onTriggerEvent = {
                            destinationsViewModel.onTriggerEvent(it)
                        },
                        clickDetail = {
                            if (it != null) {
                                navigateToDestination.invoke(it)
                            }
                        })
                }

                item {
                    Row(
                        modifier = Modifier.padding(
                            start = 5.dp,
                            end = 5.dp,
                            bottom = 10.dp,
                            top = 10.dp
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Hotels",
                            style = MaterialTheme.typography.h2,
                            textAlign = TextAlign.Start,
                            fontSize = 20.sp,
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            modifier = Modifier
                                .clickable(onClick = { navigateToHotels.invoke() }),
                            text = "View All",
                            style = MaterialTheme.typography.subtitle2.copy(color = Color.Gray)
                        )
                    }
                }

                item {
                    VerticalGrid {
                        pagingHotelItems?.let { it ->
                            it.itemSnapshotList.map {
                                Box() {
                                    HobbyHorseToursHotelsCard(
                                        status = Status.Unknown,
                                        detailClick = {
                                            if (it != null) {
                                                navigateToHotel.invoke(it)
                                            }
                                        },
                                        dto = it,
                                        onTriggerEvent = {
                                            //onTriggerEvent.invoke( SearchViewEvent.UpdateHotelFavorite(it) )
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ContentDestinations(
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
//            .padding(horizontal = 10.dp),
    ) {

            if (isLoading) {
//                items(10) {
//                    //HobbyHorseToursCharacterShimmer()
//                }
            } else if (pagedData != null) {

                    LazyRow( modifier = Modifier.fillMaxWidth() ) {
                        items(items = pagingItems!!, key = { item -> item.id as Any }) { item ->
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
    }
}

@Composable
private fun ContentHotels(
    isLoading :Boolean = false,
    pagedData: Flow<PagingData<HotelDto>>? = null,
    onTriggerEvent: (HotelsViewEvent) -> Unit,
    clickDetail: (HotelDto?) -> Unit
) {
    var pagingItems: LazyPagingItems<HotelDto>? = null
    val context = LocalContext.current

    pagedData?.let {
        pagingItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(horizontal = 10.dp),
    ) {

            if (isLoading) {

            } else if (pagedData != null) {

                    LazyRow( modifier = Modifier.fillMaxWidth() ) {
                        items(items = pagingItems!!, key = { item -> item.id as Any }) { item ->
                            HobbyHorseToursHotelsCard(
                                status = Status.Alive, //item?.status ?: Status.Unknown,
                                detailClick = {
                                    clickDetail.invoke(item)
                                },
                                dto = item,
                                onTriggerEvent = {
//                                    onTriggerEvent.invoke(
//                                        DestinationsViewEvent.UpdateDestinationFavorite(
//                                            it
//                                        )
//                                    )
                                }
                            )
                        }
                    }
            }
        }
//    }
}

@Composable
private fun ContentCities(
    isLoading :Boolean = false,
    pagedData: Flow<PagingData<CityDto>>? = null,
    onTriggerEvent: (CityViewEvent) -> Unit,
    clickDetail: (CityDto?) -> Unit
) {
    var pagingItems: LazyPagingItems<CityDto>? = null
    val context = LocalContext.current

    pagedData?.let {
        pagingItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(horizontal = 5.dp),
    ) {

            if (isLoading) {

            } else if (pagedData != null) {
                    LazyRow( modifier = Modifier.fillMaxWidth()) {
                        items(items = pagingItems!!, key = { item -> item.id as Any }) { item ->
                            if (item != null) {
                                RoundedCornerIconButtonCity(
                                    modifier = Modifier,
                                    item
                                )
                            }
                            Spacer(modifier = Modifier.size(10.dp))
                        }
//                    }
                }

            }
        }
//    }
}

@Composable
private fun ContentTravelStyle(
    isLoading :Boolean = false,
    pagedData: Flow<PagingData<TravelStyleDto>>? = null,
    onTriggerEvent: (TravelStyleViewEvent) -> Unit,
    clickDetail: (TravelStyleDto?) -> Unit
) {
    var pagingItems: LazyPagingItems<TravelStyleDto>? = null
    val context = LocalContext.current

    pagedData?.let {
        pagingItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(horizontal = 5.dp),
    ) {

            if (isLoading) {

            } else if (pagedData != null) {

                    LazyRow( modifier = Modifier.fillMaxWidth()) {
                        items(items = pagingItems!!, key = { item -> item.id as Any }) { item ->
                            if (item != null) {
                                RoundedCornerIconButtonTravelStyle(
                                    modifier = Modifier,
                                    item
                                )

                                Spacer(modifier = Modifier.size(10.dp))
                            }
                        }
//                    }
                }

            }
        }
//    }
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
    HomeScreen(
        destinationsViewModel = hiltViewModel(),
        homeViewModel = hiltViewModel(),
        cityviewModel = hiltViewModel(),
        travelstyleviewModel = hiltViewModel(),
        hotelsviewModel = hiltViewModel(),
        navigateToSearch = {},
        navigateToCities = {},
        navigateToTravelStyles = {},
        navigateToDestinations = {},
        navigateToHotels = {},
        navigateToDestination = {},
    ) {}
}