@file:OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.search

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.data.model.Status
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.HotelDto
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.TravelStyleDto
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.search.SearchViewState
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursButton
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursCharacterShimmer
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursDestinationsCard
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursHotelsCard
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursSearchBar
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursSelectableText
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursText
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursTopBar
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/**
 * Created by brendenozie on 9.04.2023
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    navigateToHotelDto: (HotelDto) -> Unit,
    navigateToBack: () -> Unit,
    navigateToDestinationDto: (DestinationDto) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded })

    val viewState by viewModel.uiState.collectAsState()
    val scope = rememberCoroutineScope()

    HobbyHorseToursScaffold(
        topBar = {
            HobbyHorseToursTopBar(
                elevation = 10.dp,
                navigationIcon = {
                    IconButton(onClick = {
                        navigateToBack()
                    }) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left),
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        scope.launch {
                            state.show()
                        }
                    }) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_filter),
                            contentDescription = null
                        )
                    }
                },
                text = stringResource(id = R.string.search_screen_title)
            )
        },
        content = {
            ModalBottomSheetLayout(
                sheetState = state,
                sheetShape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                sheetContent = {
                    BottomSheetLayout(viewModel, viewState, state)
                }
            ) {
                Content(
                    isLoading = viewState.isLoading,
                    searchText = viewState.searchText,
                    pagedHotelData = viewState.pagedHotelData,
                    pagedDestinationData = viewState.pagedDestinationData,
                    onTriggerEvent = {
                        viewModel.onTriggerEvent(it)
                    },
                    clickHotelDetail = {
                        if (it != null) {
                            navigateToHotelDto.invoke(it)
                        }
                    },
                    clickDestinationDetail = {
                        if (it != null) {
                            navigateToDestinationDto.invoke(it)
                        }
                    },
                    onTextChange = {
                        viewModel.searchText(it)
                    }
                )
            }
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background
    )

}

@Composable
private fun Content(
    isLoading: Boolean,
    searchText: String?,
    pagedHotelData: Flow<PagingData<HotelDto>>?,
    pagedDestinationData: Flow<PagingData<DestinationDto>>?,
    onTriggerEvent: (SearchViewEvent) -> Unit,
    clickHotelDetail: (HotelDto?) -> Unit,
    clickDestinationDetail: (DestinationDto?) -> Unit,
    onTextChange: (String) -> Unit
) {

    var pagingHotelItems: LazyPagingItems<HotelDto>? = null
    pagedHotelData?.let {
        pagingHotelItems = Utility.rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    var pagingDestinationItems: LazyPagingItems<DestinationDto>? = null
    pagedDestinationData?.let {
        pagingDestinationItems = Utility.rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }


    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp),
    ) {
        HobbyHorseToursSearchBar(
            modifier = Modifier.padding(top = 15.dp),
            text = searchText.orEmpty(),
            onTextChange = {
                onTextChange.invoke(it)
            },
            onClickSearch = {
                onTriggerEvent.invoke(SearchViewEvent.NewSearchEvent)
                keyboardController?.hide()
            }
        )
        ShowSearchList(isLoading, pagingHotelItems, pagedHotelData, pagingDestinationItems, pagedDestinationData, clickHotelDetail, clickDestinationDetail, onTriggerEvent)
    }
}

@Composable
private fun ShowSearchList(
    isLoading: Boolean,
    pagingHotelItems: LazyPagingItems<HotelDto>?,
    pagedHotelData: Flow<PagingData<HotelDto>>?,
    pagingDestinationItems: LazyPagingItems<DestinationDto>?,
    pagedDestinationData: Flow<PagingData<DestinationDto>>?,
    clickHotelDetail: (HotelDto?) -> Unit,
    clickDestinationDetail: (DestinationDto?) -> Unit,
    onTriggerEvent: (SearchViewEvent) -> Unit
) {

        if (isLoading) {
            LazyColumn(
                contentPadding = PaddingValues(vertical = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(10) {
                    HobbyHorseToursCharacterShimmer()
                }
            }
        } else if (pagedHotelData != null && pagingHotelItems != null && pagedDestinationData != null && pagingDestinationItems != null) {


//            item {
                Row(
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Destinations",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp
                    )
//                        Text(
//                            modifier = Modifier
//                                .clickable(onClick = { navigateToCities.invoke() }),
//                            text = "View All",
//                            style = MaterialTheme.typography.subtitle2.copy(color = Color.Gray)
//                        )
                }
//            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(2.dp)
            ) {

                items(
                    pagingDestinationItems.itemCount
                ) { index ->
                    pagingDestinationItems[index]?.let {
                        Box(Modifier.padding(2.dp)) {
                            HobbyHorseToursDestinationsCard(
                                status = Status.Unknown,
                                detailClick = {
                                    clickDestinationDetail.invoke(it)
                                },
                                dto = it,
                                onTriggerEvent = {
                                    onTriggerEvent.invoke(
                                        SearchViewEvent.UpdateDestinationFavorite(
                                            it
                                        )
                                    )
                                }
                            )
                        }
                    }
                }

//            items(items = pagingHotelItems) { item ->
//                HobbyHorseToursHotelsCard(
//                    status = Status.Unknown,
//                    detailClick = {
//                        clickHotelDetail.invoke(item)
//                    },
//                    dto = item,
//                    onTriggerEvent = {
////                        onTriggerEvent.invoke(SearchViewEvent.UpdateFavorite(it))
//                    }
//                )
//            }
            }


             Spacer(modifier = Modifier.size(10.dp))

             Row(
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hotels",
                        modifier = Modifier.weight(1f),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp
                    )
//                        Text(
//                            modifier = Modifier
//                                .clickable(onClick = { navigateToCities.invoke() }),
//                            text = "View All",
//                            style = MaterialTheme.typography.subtitle2.copy(color = Color.Gray)
//                        )
                }


            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(2.dp)
            ) {

            items(
                    pagingHotelItems.itemCount
                ) { index ->
                    pagingHotelItems[index]?.let {
                        Box(Modifier.padding(2.dp)) {
                            HobbyHorseToursHotelsCard(
                                status = Status.Unknown,
                                detailClick = {
                                    clickHotelDetail.invoke(it)
                                },
                                dto = it,
                                onTriggerEvent = {
                                    onTriggerEvent.invoke(SearchViewEvent.UpdateHotelFavorite(it))
                                }
                            )
                        }
                    }
                }


            }

//            items(items = pagingDestinationItems) { item ->
//                HobbyHorseToursDestinationsCard(
//                    status = Status.Unknown,
//                    detailClick = {
//                        clickDestinationDetail.invoke(item)
//                    },
//                    dto = item,
//                    onTriggerEvent = {
////                        onTriggerEvent.invoke(SearchViewEvent.UpdateFavorite(it))
//                    }
//                )
//            }
//        }
    }
}

@Composable
private fun BottomSheetLayout(
    viewModel: SearchViewModel,
    viewState: SearchViewState,
    state: ModalBottomSheetState
) {
    val scope = rememberCoroutineScope()

    var pagingCityItems: LazyPagingItems<CityDto>? = null
    viewState.pagedCityData?.let {
        pagingCityItems = Utility.rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    var pagingTravelStyleItems: LazyPagingItems<TravelStyleDto>? = null
    viewState.pagedTravelStyleData?.let {
        pagingTravelStyleItems = Utility.rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
    }

    ConstraintLayout(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
            .padding(horizontal = 20.dp, vertical = 15.dp)
    ) {
        val (title, divider, description, button) = createRefs()
        HobbyHorseToursText(
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent

            },
            text = stringResource(id = R.string.search_modal_title)
        )
        Divider(
            modifier = Modifier
                .constrainAs(divider) {
                    top.linkTo(title.bottom)
                    start.linkTo(title.start)
                    end.linkTo(title.end)
                    width = Dimension.fillToConstraints

                }
                .background(color = MaterialTheme.colors.primaryVariant)

        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
                .constrainAs(description) {
                    top.linkTo(divider.bottom)
                },
        ) {
            HobbyHorseToursText(
                text = stringResource(id = R.string.search_modal_city_title),
                color = MaterialTheme.colors.primary
            )
            LazyRow( modifier = Modifier.fillMaxWidth() ) {
                if(pagingCityItems !=null) {
                    items(items = pagingCityItems!!) { item ->
                        if (item != null) {
                            HobbyHorseToursSelectableText(
                                modifier = Modifier.weight(1f),
                                isSelected = viewState.selectedCityData!=null && viewState.selectedCityData.cityName == item.cityName,
                                text = item.cityName
                            ) {
                                viewModel.selectCity(item)
                            }
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                    }
//                Spacer(modifier = Modifier.width(20.dp))
                }
            }

            HobbyHorseToursText(
                text = stringResource(id = R.string.search_modal_travelstyle_title),
                modifier = Modifier.padding(top = 10.dp),
                color = MaterialTheme.colors.primary
            )
            LazyRow( modifier = Modifier.fillMaxWidth() ) {
                if(pagingCityItems !=pagingTravelStyleItems) {
                    items(items = pagingTravelStyleItems!!) { itm ->
                        if (itm != null) {
                            itm.styleName?.let {
                                HobbyHorseToursSelectableText(
                                    modifier = Modifier.weight(0.5f),
                                    isSelected = viewState.selectedTravelStyleData!=null && viewState.selectedTravelStyleData.styleName == itm.styleName,
                                    text = it,
                                    style = MaterialTheme.typography.body2
                                ) {
                                    viewModel.selectTravelStyle(itm)
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            }
        }
        HobbyHorseToursButton(
            modifier = Modifier.constrainAs(button) {
                bottom.linkTo(parent.bottom, 30.dp)
            },
            onClick = {
                viewModel.onTriggerEvent(SearchViewEvent.NewSearchEvent)
                scope.launch {
                    state.hide()
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary),
            borderColor = MaterialTheme.colors.primary,
            text = stringResource(id = R.string.search_modal_button_text)
        )
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
    SearchScreen(
        viewModel = hiltViewModel(),
        navigateToHotelDto = {},
        navigateToBack = {},
        navigateToDestinationDto = {}
    )
}