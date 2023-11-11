package ke.co.tulivuapps.hobbyhorsetours.features.screen.homee

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.data.model.Status
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.home.HomeViewState
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursDestinationsCard
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursEpisodesShimmer
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursHotelsCard
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursText
import ke.co.tulivuapps.hobbyhorsetours.features.component.RoundedCornerIconButtonCity
import ke.co.tulivuapps.hobbyhorsetours.features.component.RoundedCornerIconButtonTravelStyle
import ke.co.tulivuapps.hobbyhorsetours.features.component.SearchBox
import ke.co.tulivuapps.hobbyhorsetours.features.component.SlidingBanner
import ke.co.tulivuapps.hobbyhorsetours.features.component.TopBar
import ke.co.tulivuapps.hobbyhorsetours.features.screen.cities.CityViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.screen.destinations.DestinationsViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.screen.hotels.HotelsViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.screen.travelstyles.TravelStyleViewEvent
import ke.co.tulivuapps.hobbyhorsetours.features.ui.theme.Dimension
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility.rememberFlowWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by brendenozie on 13.03.2023
 */

@Composable
fun HomeeScreen(
    homeViewModel: HomeeViewModel,
    navigateToLogin: () -> Unit?,
    navigateToSearch: () -> Unit?,
    navigateToCities: () -> Unit?,
    navigateToTravelStyles: () -> Unit?,
    navigateToDestinations: () -> Unit?,
    navigateToHotels: () -> Unit?,
    navigateToDestination: (DestinationDto) -> Unit?,
    navigateToHotel: (HotelDto) -> Unit?
) {

    val scaffoldState = rememberScaffoldState()
    val homeViewState by  homeViewModel.uiState.collectAsState()
    val isLoggedIn by homeViewModel.selectedLogin.collectAsState()
    val username = homeViewModel.selectedUsername.collectAsState().value


    LaunchedEffect(homeViewModel.uiEvent) {
        launch {
            homeViewModel.uiEvent.collect {
                when (it) {
                    is HomeViewEvent.SnackBarError -> {
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
            TopBar(onNavigationIconClick = { navigateToLogin.invoke() }, isLoggedIn, username )
        },
        content = {
            Content(
                homeViewState = homeViewState,
                reload = { homeViewModel.loadcontent() },
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
    homeViewState: HomeViewState,
    reload: () -> Unit?,
    navigateToSearch: () -> Unit?,
    navigateToCities: () -> Unit?,
    navigateToTravelStyles: () -> Unit?,
    navigateToDestinations: () -> Unit?,
    navigateToHotels: () -> Unit?,
    navigateToDestination: (DestinationDto) -> Unit?,
    navigateToHotel: (HotelDto) -> Unit?
) {

    val searchVisibility = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        delay(1000L)
        searchVisibility.value = true
    }

    var pagingHotelItems: LazyPagingItems<HotelDto>? = null

    homeViewState.pagedHotelData?.let {
        pagingHotelItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    var pagingCityItems: LazyPagingItems<CityDto>? = null

    homeViewState.pagedCityData?.let {
        pagingCityItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    var pagingDestinationItems: LazyPagingItems<DestinationDto>? = null

    homeViewState.pagedDestinationData?.let {
        pagingDestinationItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    var pagingTravelStyleItems: LazyPagingItems<TravelStyleDto>? = null

    homeViewState.pagedTravelStyleData?.let {
        pagingTravelStyleItems = rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    val state = rememberLazyGridState()
    AnimatedVisibility(visible = searchVisibility.value,
        enter = expandIn(
        tween(
            delayMillis = 1100,
            easing = LinearOutSlowInEasing,
            durationMillis = 500
        ),expandFrom = Alignment.Center
    ) { IntSize(0, 0) },) {
        LazyVerticalGrid(
            modifier = Modifier
                .animateContentSize()
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(Dimension.zero),
            verticalArrangement = Arrangement.spacedBy(Dimension.zero),
            contentPadding = PaddingValues(horizontal = Dimension.zero),
            state = state
        ) {

            if (!homeViewState.isLoading && pagingCityItems != null && pagingCityItems?.itemCount!! == 0 &&
                pagingDestinationItems != null && pagingDestinationItems?.itemCount!! == 0 &&
                pagingTravelStyleItems != null && pagingTravelStyleItems?.itemCount!! == 0 &&
                pagingHotelItems != null && pagingHotelItems?.itemCount!! == 0
            ) {

                item(span = { GridItemSpan(maxLineSpan) }, key = null, contentType = "A") {
                    EmptyListAnimation(reload)
                }

            }

            if (pagingCityItems != null && pagingCityItems?.itemCount!! > 0 &&
                pagingDestinationItems != null && pagingDestinationItems?.itemCount!! > 0 &&
                pagingTravelStyleItems != null && pagingTravelStyleItems?.itemCount!! > 0 &&
                pagingHotelItems != null && pagingHotelItems?.itemCount!! > 0
            ) {
                item(span = { GridItemSpan(maxLineSpan) }, key = null, contentType = "A") {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                item(span = { GridItemSpan(maxLineSpan) }, key = null) {
                        SearchBox { navigateToSearch.invoke() }
                }
                item(span = { GridItemSpan(maxLineSpan) }, key = null, contentType = "A") {
                    Spacer(modifier = Modifier.size(10.dp))
                }
                item(span = { GridItemSpan(maxLineSpan) }, key = null) {
                        SlidingBanner()
                }
                item(span = { GridItemSpan(maxLineSpan) }, key = null, contentType = "A") {
                    Spacer(modifier = Modifier.size(10.dp))
                }

                contentCities(isLoading = homeViewState.isLoading,
                    pagingItems = pagingCityItems,
                    onTriggerEvent = {
                        //cityviewModel.onTriggerEvent(it)
                    },
                    navigateToCities = { navigateToCities.invoke() },
                    clickDetail = {
                        //navigatetoc.invoke(it)
                    }
                )

                contentTravelStyle(isLoading = homeViewState.isLoading,
                    pagingItems = pagingTravelStyleItems,
                    onTriggerEvent = {
                        //homeViewState.onTriggerEvent(it)
                    },
                    navigateToTravelStyles = { navigateToTravelStyles.invoke() },
                    clickDetail = {
                        navigateToTravelStyles.invoke()
                    }
                )

                contentDestinations(isLoading = homeViewState.isLoading,
                    pagingItems = pagingDestinationItems,
                    onTriggerEvent = {
                        //destinationsViewModel.onTriggerEvent(it)
                    },
                    navigateToDestinations = { navigateToDestinations.invoke() },
                    clickDetail = {
                        if (it != null) {
                            navigateToDestination.invoke(it)
                        }
                    })

                contentHotels(isLoading = homeViewState.isLoading,
                    pagingItems = pagingHotelItems,
                    onTriggerEvent = {
                        //hotelsViewModel.onTriggerEvent(it)
                    },
                    navigateToHotels = { navigateToHotels.invoke() },
                    clickDetail = {
                        if (it != null) {
                            navigateToHotel.invoke(it)
                        }
                    }
                )
            }

            if (homeViewState.isLoading) {
                items(10, span = { GridItemSpan(maxLineSpan) }, key = null,) {
                    HobbyHorseToursEpisodesShimmer()
                }
            }

        }
    }
}

private fun LazyGridScope.contentCities(
    isLoading :Boolean = false,
    pagingItems: LazyPagingItems<CityDto>? = null,
    onTriggerEvent: (CityViewEvent) -> Unit,
    navigateToCities: () -> Unit,
    clickDetail: (CityDto?) -> Unit
) {
    item(span = { GridItemSpan(maxLineSpan) }, key = null, contentType = "F") {
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
    item(span = { GridItemSpan(maxLineSpan) }, key = null) {
        if (!isLoading && pagingItems != null) {
            val state = rememberLazyListState()
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(5.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                state = state
            ) {

                items(items = pagingItems, key = null) { item ->
                    if (item != null) {
                        RoundedCornerIconButtonCity(
                            modifier = Modifier,
                            item
                        )
                    }
                }
            }
        }
    }
}

private fun LazyGridScope.contentTravelStyle(
    isLoading :Boolean = false,
    pagingItems: LazyPagingItems<TravelStyleDto>? = null,
    navigateToTravelStyles: () -> Unit,
    onTriggerEvent: (TravelStyleViewEvent) -> Unit,
    clickDetail: (TravelStyleDto?) -> Unit
) {

    item(span = { GridItemSpan(maxLineSpan) }, key = null, contentType = "F") {
            Row(
                Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Travelling Style",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier
                        .clickable(onClick = { navigateToTravelStyles.invoke() }),
                    text = "View All",
                    style = MaterialTheme.typography.subtitle2.copy(color = Color.Gray)
                )
            }
        }

    item(span = { GridItemSpan(maxLineSpan) }, key = null, contentType = "J") {

        if (!isLoading && pagingItems != null) {
            val state = rememberLazyListState()
            LazyRow( modifier = Modifier
                .fillMaxWidth(),
                contentPadding = PaddingValues(5.dp),
                horizontalArrangement= Arrangement.spacedBy(5.dp),
                state=state
            ) {
                items(items = pagingItems, key = null) { item ->
                    if (item != null) {
                            RoundedCornerIconButtonTravelStyle(
                                modifier = Modifier,
                                item
                            )
                    }
                }
            }
        }
    }
}

private fun LazyGridScope.contentDestinations(
    isLoading :Boolean = false,
    pagingItems: LazyPagingItems<DestinationDto>?,
    navigateToDestinations: () -> Unit?,
    onTriggerEvent: (DestinationsViewEvent) -> Unit,
    clickDetail: (DestinationDto?) -> Unit
) {
    item(span = { GridItemSpan(maxLineSpan) }, key = null, contentType = "F") {
            Row(
                modifier = Modifier.animateContentSize().padding(start = 5.dp, end = 5.dp, bottom = 10.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Destinations",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier
                        .clickable(onClick = { navigateToDestinations.invoke() }),
                    text = "View All",
                    style = MaterialTheme.typography.subtitle2.copy(color = Color.Gray)
                )
            }
        }

    item(span = { GridItemSpan(maxLineSpan) }, key = null, contentType = "G") {
        if (!isLoading && pagingItems != null) {
            val state = rememberLazyListState()
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                contentPadding = PaddingValues(5.dp),
                horizontalArrangement= Arrangement.spacedBy(5.dp),
                state = state
            ) {
                items(items = pagingItems, key = null) { item ->
                        HobbyHorseToursDestinationsCard(
                            status = Status.Alive,
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

private fun LazyGridScope.contentHotels(
    isLoading :Boolean = false,
    pagingItems: LazyPagingItems<HotelDto>? = null,
    navigateToHotels: () -> Unit?,
    onTriggerEvent: (HotelsViewEvent) -> Unit,
    clickDetail: (HotelDto?) -> Unit
) {
    item(span = { GridItemSpan(maxLineSpan) }, key = null, contentType = "F") {
            Row(
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 10.dp, top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Hotels",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier.clickable(onClick = { navigateToHotels.invoke() }),
                    text = "View All",
                    style = MaterialTheme.typography.subtitle2.copy(color = Color.Gray)
                )
            }
        }

    if (!isLoading && pagingItems != null){
        pagingItems.let { item ->
            items(item.itemCount, key = null, contentType = {1}) {
                    HobbyHorseToursHotelsCard(
                        status = Status.Alive,
                        detailClick = {
                            clickDetail.invoke(item[it])
                        },
                        dto = item[it],
                        onTriggerEvent = { item ->
                            onTriggerEvent.invoke(HotelsViewEvent.UpdateHotelFavorite(item))
                        }
                    )
                }
            }
        }
    }

@Composable
private fun EmptyListAnimation(reload: () -> Unit?) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_search))
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(
                composition,
                modifier = Modifier,
                restartOnPlay = true,
                alignment = Alignment.Center,
                iterations = LottieConstants.IterateForever,
            )
            HobbyHorseToursText(
                text = stringResource(R.string.empty_screen_empty_list_text),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .wrapContentHeight()
                    .clickable { reload() },
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.subtitle1
            )
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
    HomeeScreen(
        homeViewModel = hiltViewModel(),
        navigateToLogin = {},
        navigateToSearch = {},
        navigateToCities = {},
        navigateToTravelStyles = {},
        navigateToDestinations = {},
        navigateToHotels = {},
        navigateToDestination = {},
    ) {}
}