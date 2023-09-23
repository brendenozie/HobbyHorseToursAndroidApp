package ke.co.tulivuapps.hobbyhorsetours.features.screen.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursCharacterShimmer
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursDestinationsCard
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursHotelsCard
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.features.component.RoundedCornerIconButtonCity
import ke.co.tulivuapps.hobbyhorsetours.features.component.RoundedCornerIconButtonTravelStyle
import ke.co.tulivuapps.hobbyhorsetours.features.component.SearchBox
import ke.co.tulivuapps.hobbyhorsetours.features.component.SlidingBanner
import ke.co.tulivuapps.hobbyhorsetours.features.component.TopBar
import ke.co.tulivuapps.hobbyhorsetours.features.screen.cities.CityViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.screen.cities.CityViewModel
import ke.co.tulivuapps.hobbyhorsetours.features.screen.destinations.DestinationsViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.screen.destinations.DestinationsViewModel
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.HotelsViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.HotelsViewModel
import ke.co.tulivuapps.hobbyhorsetours.features.screen.travelstyles.TravelStyleViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.screen.travelstyles.TravelStyleViewModel
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.rememberFlowWithLifecycle
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 13.03.2023
 */

@Composable
fun HomeScreen(
    destinationsViewModel: DestinationsViewModel,
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

    HobbyHorseToursScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(onNavigationIconClick = {    })
        },
        content = {
            Content(
                destinationsViewModel = destinationsViewModel,
                travelstyleviewModel = travelstyleviewModel,
                cityviewModel = cityviewModel,
                hotelsviewModel = hotelsviewModel,
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

@Composable
private fun Content(
    isLoading:Boolean = false,
    destinationsViewModel: DestinationsViewModel,
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

    val destinationsViewState = destinationsViewModel.uiState.collectAsState().value
    val travelStyleViewState = travelstyleviewModel.uiState.collectAsState().value
    val cityViewState = cityviewModel.uiState.collectAsState().value
    val hotelsViewState = hotelsviewModel.uiState.collectAsState().value

    var pagingHotelItems: LazyPagingItems<HotelDto>? = null
    hotelsViewState.pagedData?.let {
        pagingHotelItems = Utility.rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(2.dp),

            ) {

            if (isLoading) {
                items(10) {
                    HobbyHorseToursCharacterShimmer()
                }
            } else {
                item{
                    Spacer(modifier = Modifier.size(10.dp))
                }

                item(span = { GridItemSpan(maxLineSpan) }){
                    SearchBox { navigateToSearch.invoke() }
                }

                item{
                    Spacer(modifier = Modifier.size(10.dp))
                }
                item(span = { GridItemSpan(maxLineSpan)}) {
                    SlidingBanner()
                }
                item{
                    Spacer(modifier = Modifier.size(10.dp))
                }
                item(span = { GridItemSpan(maxLineSpan)}) {
                    Row(
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp, top = 10.dp),
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
                item(span = { GridItemSpan(maxLineSpan)}) {

                    ContentCities(isLoading = cityViewState.isLoading,
                                    pagedData = cityViewState.pagedData,
                                    onTriggerEvent = {
                                        cityviewModel.onTriggerEvent(it)
                                    },
                                    clickDetail = {
                                        //navigateToDestination.invoke(it)
                                    } )
                }

                item(span = { GridItemSpan(maxLineSpan)}) {
                    Row(
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp, top = 10.dp),
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
                item(span = { GridItemSpan(maxLineSpan)}) {

                    ContentTravelStyle(isLoading = travelStyleViewState.isLoading,
                        pagedData = travelStyleViewState.pagedData,
                        onTriggerEvent = {
                            travelstyleviewModel.onTriggerEvent(it)
                        },
                        clickDetail = {
                            //navigateToDestination.invoke(it)
                        } )

                }

                item(span = { GridItemSpan(maxLineSpan)}) {
                    Row(
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp, top = 10.dp),
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

                item(span = { GridItemSpan(maxLineSpan)}) {
                    ContentDestinations(isLoading = destinationsViewState.isLoading,
                                        pagedData = destinationsViewState.pagedData,
                                        onTriggerEvent = {
                                            destinationsViewModel.onTriggerEvent(it)
                                        },
                                        clickDetail = {
                                            if (it != null) {
                                                navigateToDestination.invoke(it)
                                            }
                                        } )
                }

                item(span = { GridItemSpan(maxLineSpan)}) {
                    Row(
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp, top = 10.dp),
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

//                item{
//                    ContentHotels(isLoading = hotelsViewState.isLoading,
//                                    pagedData = hotelsViewState.pagedData,
//                                    onTriggerEvent = {
//                                        hotelsviewModel.onTriggerEvent(it)
//                                    },
//                                    clickDetail = {
//                                        if (it != null) {
//                                            navigateToHotel.invoke(it)
//                                        }
//                                    } )
//                }

                pagingHotelItems?.let { it ->
                    items(
                        it.itemCount
                    ) { index ->
                        pagingHotelItems!![index]?.let {
//                            Box(Modifier.padding(2.dp)) {
                                HobbyHorseToursHotelsCard(
                                    status = Status.Unknown,
                                    detailClick = { navigateToHotel.invoke(it) },
                                    dto = it,
                                    onTriggerEvent = {
            //                                    onTriggerEvent.invoke(
            //                                        SearchViewEvent.UpdateHotelFavorite(
            //                                            it
            //                                        )
            //                                    )
                                    }
                                )
//                            }
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
            } else if (pagedData != null && pagingItems != null) {

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

            }else{
                Toast.makeText(context, "Nothing To Show", Toast.LENGTH_SHORT).show()
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

            } else if (pagedData != null && pagingItems != null) {

                    LazyRow( modifier = Modifier.fillMaxWidth() ) {
                        items(items = pagingItems!!) { item ->
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
            }else{
                Toast.makeText(context, "Nothing To Show", Toast.LENGTH_SHORT).show()
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
                    LazyRow( modifier = Modifier.fillMaxWidth() ) {
                        items(items = pagingItems!!) { item ->
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

            }else{
                Toast.makeText(context, "Nothing To Show", Toast.LENGTH_SHORT).show()
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

                    LazyRow( modifier = Modifier.fillMaxWidth() ) {
                        items(items = pagingItems!!) { item ->
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

            }else{
                Toast.makeText(context, "Nothing To Show", Toast.LENGTH_SHORT).show()
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
    HomeScreen(destinationsViewModel = hiltViewModel(),
                cityviewModel = hiltViewModel(),
                travelstyleviewModel = hiltViewModel(),
                hotelsviewModel = hiltViewModel(),
                navigateToSearch = {},
                navigateToCities = {},
                navigateToTravelStyles = {},
                navigateToDestinations = {},
                navigateToHotels = {},
                navigateToDestination = {},) {}
}