package ke.co.tulivuapps.hobbyhorsetours.features.screen.hotelsdetail

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.data.model.img
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.hotelsdetail.HotelsDetailViewState
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursNetworkImage
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursTopBar
import kotlinx.coroutines.launch

/**
 * Created by brendenozie on 13.03.2023
 */

//New Screen

@Composable
fun DetailScreen(
    viewModel: HotelsDetailViewModel = viewModel(),
    navigateToBookNow: (Result?)-> Unit,
    navigateToBack: () -> Unit
) {
    val viewState by viewModel.uiState.collectAsState()
    val scaffoldState: ScaffoldState = rememberScaffoldState()

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is HotelsDetailViewEvent.SnackBarError -> {
                        scaffoldState.snackbarHostState.showSnackbar(it.message.orEmpty())
                    }
                }
            }
        }
    }

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
                    IconButton(onClick = {}) {}
                },
                text = stringResource(id = R.string.character_detail_screen_title)
            )
        },
        content = {innerPadding ->
            DetailScreenContent(modifier = Modifier.padding(innerPadding),
                viewState= viewState,
                navigateToBookNow = {
                    navigateToBookNow.invoke(it)
                })
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
private fun CharacterImage(data: Result?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(top = 20.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            1.dp,
            color = Color.LightGray //if (data?.status == Status.Alive) Color.Green else Color.Red
        ),
    ) {
        HobbyHorseToursNetworkImage(
            imageURL = data?.img?.get(0)?.url,
            modifier = Modifier
                .fillMaxSize(),
            placeholder = R.drawable.ic_place_holder,
            contentScale = ContentScale.FillBounds,
        )
    }
}


@Composable
private fun DetailScreenContent(
    modifier: Modifier,
    viewState:HotelsDetailViewState,
    navigateToBookNow: (Result?)-> Unit
) {

    LazyColumn(
        modifier = modifier
            .padding(start = 14.dp, end = 14.dp)
            .fillMaxHeight()
            .semantics { contentDescription = "Detail Screen" }
    ) {
        item(key = null) {
            CharacterImage(
                viewState.data
            )
        }
        item(key = null) {
            Spacer(modifier = Modifier.size(16.dp))
        }
        item(key = null) {
            Row(
                Modifier
                    .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
            ) {
                viewState.data?.let { Text(text = it.title, style = MaterialTheme.typography.h5) }
                Spacer(modifier = Modifier.weight(1F))
                Image(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = null,
                    Modifier.size(10.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = "(4.8)", style = MaterialTheme.typography.caption)
            }
        }
        item(key = null) {
            Spacer(modifier = Modifier.size(16.dp))
        }
        item(key = null) {
            viewState.data?.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body2
                )
            }
        }
        item(key = null) {
            Spacer(modifier = Modifier.size(16.dp))
        }
        item(key = null) {
            viewState.data?.let { MoreImages(it.img) }
        }
        item(key = null) {
            Spacer(modifier = Modifier.size(16.dp))
        }
        item(key = null) {
            Row(Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(text = "Total Price", style = MaterialTheme.typography.h6)
                    Spacer(modifier = Modifier.size(12.dp))
                    Row {
                        Text(text = "", style = MaterialTheme.typography.subtitle2)
                        viewState.data?.price?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.subtitle2
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1F))
                Button(
                    onClick = { navigateToBookNow.invoke(viewState.data) },
                    modifier = Modifier
                        .padding(bottom = 56.dp)
                        .size(170.dp, 56.dp),
                    shape = RoundedCornerShape(72.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)
                ) {
                    Text(text = "Book Now", style = MaterialTheme.typography.button)
                }
            }
        }
    }
}

@Composable
private fun MoreImages(img: List<img>) {

    Spacer(modifier = Modifier.size(24.dp))
    Column(
        Modifier
            .fillMaxWidth()) {
        Text(text = "More Images", style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.size(16.dp))
        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            img.map {
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = it.url).apply(block = fun ImageRequest.Builder.() {
                        crossfade(false)
                        size(Size(1200,900))
                        error(R.drawable.coffee)
                        fallback(R.drawable.coffee)
                    }).build()
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier.size(70.dp)
                )
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
    DetailScreen(viewModel = hiltViewModel(), navigateToBack = {}, navigateToBookNow = {})
}
