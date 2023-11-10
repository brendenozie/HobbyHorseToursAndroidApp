package ke.co.tulivuapps.hobbyhorsetours.features.screen.booking

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.BookingDto
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursBookingCard
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursEpisodesShimmer
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursText
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursTopBar
import ke.co.tulivuapps.hobbyhorsetours.utils.Utility
import kotlinx.coroutines.flow.Flow

/**
 * Created by brendenozie on 30.03.2023
 */

@Composable
fun BookingsScreen(
    viewModel: BookingsViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    navigateBookingDetail: (BookingDto) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val viewState = viewModel.uiState.collectAsState().value


//    LaunchedEffect(viewModel.uiEvent) {
//        launch {
//            viewModel.uiEvent.collect {
//                when (it) {
//                    is BookingsViewEvent.SnackBarError -> {
//                        scaffoldState.snackbarHostState.showSnackbar(it.message.orEmpty())
//                    }
//                }
//            }
//        }
//    }

    HobbyHorseToursScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = {
            HobbyHorseToursTopBar(
                text = stringResource(id = R.string.booking_screen_title),
                actions = {
                    IconButton(onClick = {  }) {
                    }
                },
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
                    if (it != null) {
                        navigateBookingDetail.invoke(it)
                    }
                },
                clickLogin = { navigateToLogin() }
            )
        },
        backgroundColor = MaterialTheme.colors.background
    )

}

@Composable
private fun Content(
    isLoading :Boolean = false,
    pagedData: Flow<PagingData<BookingDto>>? = null,
    onTriggerEvent: (BookingsViewEvent) -> Unit,
    clickDetail: (BookingDto?) -> Unit,
    clickLogin: () -> Unit
) {
    var pagingItems: LazyPagingItems<BookingDto>? = null
    val context = LocalContext.current

    pagedData?.let {
        pagingItems = Utility.rememberFlowWithLifecycle(it).collectAsLazyPagingItems()
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
                        HobbyHorseToursEpisodesShimmer()
                    }
                }

            if(pagingItems!=null && pagingItems!!.itemCount >0) {
                items(items = pagingItems!!) { item ->
                    if (item != null) {
                        HobbyHorseToursBookingCard(
                            item = item,
                            detailClick = { clickDetail.invoke(item) },
                        )
                    }
                }
            }

            if(pagingItems ==null || pagingItems!!.itemCount == 0) {
                item {
                    EmptyListAnimation(clickLogin)
                }
            }

            }
        }
    }


@Composable
private fun EmptyListAnimation(reload: () -> Unit?,) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_search))
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            LottieAnimation(
                composition,
                modifier = Modifier,
                restartOnPlay = true,
                alignment = Alignment.Center,
                iterations = LottieConstants.IterateForever,
            )
            HobbyHorseToursText(
                text = stringResource(R.string.booking_screen_empty_list_text),
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
//    Content(false, 1, false, false, listOf(), triggerEvent = {}, clickDetail = {})
}