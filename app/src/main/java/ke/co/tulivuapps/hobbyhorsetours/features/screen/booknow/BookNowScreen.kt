package ke.co.tulivuapps.hobbyhorsetours.features.screen.booknow

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.data.model.Result
import ke.co.tulivuapps.hobbyhorsetours.domain.viewstate.booknow.BookNowViewState
import ke.co.tulivuapps.hobbyhorsetours.features.component.ChildrenUserInput
import ke.co.tulivuapps.hobbyhorsetours.features.component.DatesUserInput
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursNetworkImage
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursScaffold
import ke.co.tulivuapps.hobbyhorsetours.features.component.HobbyHorseToursTopBar
import ke.co.tulivuapps.hobbyhorsetours.features.component.InputItem
import ke.co.tulivuapps.hobbyhorsetours.features.component.PeopleUserInput
import ke.co.tulivuapps.hobbyhorsetours.features.component.RoomsInput
import ke.co.tulivuapps.hobbyhorsetours.features.screen.login.HorizontalDottedProgressBar
import kotlinx.coroutines.launch

/**
 * Created by brendenozie on 30.03.2023
 */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BookNowScreen(
    viewModel: BookNowViewModel,
    navigateToLogin: () -> Unit,
    navigateToCalender: () -> Unit,
    navigateToBack: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val viewState by viewModel.uiState.collectAsState()
    val onPeopleChanged: (Int) -> Unit = { viewModel.updatePeople(it) }
    val onChildrenChanged: (Int) -> Unit = { viewModel.updateChildren(it) }
    val onRoomsChanged: (Int) -> Unit = { viewModel.updateRooms(it) }
    val selectedDates  = remember { viewModel.calendarState  }
    val scope = rememberCoroutineScope()
    val isLoggedIn = viewModel.selectedLogin

    LaunchedEffect(key1 = Unit) {
        if (!isLoggedIn) {
            navigateToLogin.invoke()
        }
    }

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is BookNowViewEvent.SnackBarError -> {
                        scaffoldState.snackbarHostState.showSnackbar(it.message.orEmpty())
                    }

                    else -> {}
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
                        navigateToBack.invoke()
                    }) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_left),
                            contentDescription = null
                        )
                    }
                },
                text = stringResource(id = R.string.booking_now_screen_title)
            )
        },
        content = {innerPadding ->
            DetailScreenContent(
                modifier = Modifier.padding(innerPadding),
                viewState = viewState,
                onPeopleChanged = onPeopleChanged,
                onChildrenChanged = onChildrenChanged,
                onRoomsChanged = onRoomsChanged,
                datesSelected = selectedDates.calendarUiState.value.selectedDatesFormatted,
                onDateSelectionClicked =  {
                    navigateToCalender.invoke()
                },
                onBook = { scope.launch{ viewModel.BookNow()}},
                navigateToBack = { navigateToBack() },
                navigateToPayment = {}
            )
        },
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background
    )

}

@Composable
private fun DetailScreenContent(
    modifier: Modifier,
    viewState: BookNowViewState,
    datesSelected: String,
    onPeopleChanged: (Int) -> Unit,
    onChildrenChanged: (Int) -> Unit,
    onRoomsChanged: (Int) -> Unit,
    onDateSelectionClicked: () -> Unit,
    onBook: () -> Unit,
    navigateToBack: () -> Unit,
    navigateToPayment: ()-> Unit
) {

    var nameText by remember { mutableStateOf(TextFieldValue()) }
    var cardNumber by remember { mutableStateOf(TextFieldValue()) }
    var expiryNumber by remember { mutableStateOf(TextFieldValue()) }
    var cvcNumber by remember { mutableStateOf(TextFieldValue()) }
    var state = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .padding(start = 14.dp, end = 14.dp)
            .fillMaxHeight()
            .semantics { contentDescription = "Detail Screen" },
        state = state
    ) {
            item(key = null) {
                CharacterImage(
                    viewState.data
                )
            }

            item(key = null) {
                Spacer(modifier = Modifier.size(16.dp))
            }

            item(key = null){
                viewState.data?.let { Text(text = it.title, style = MaterialTheme.typography.h5) }
            }

            item(key = null) {
                Row(
                    Modifier
                        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(modifier = Modifier.weight(1F),text = "${viewState.data?.location} - Show on map", style = MaterialTheme.typography.subtitle1)
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.size(2.dp))
                    Text(text = "(4.8)", style = MaterialTheme.typography.caption)
                }
            }

            item(key = null) {
                Spacer(modifier = Modifier.size(16.dp))
            }

            item(key = null) {
                Text(text = "Check In", style = MaterialTheme.typography.subtitle1)
            }

            item(key = null) {
                Spacer(modifier = Modifier.size(16.dp))
            }

            item(key = null) {
                PeopleUserInput(
                    titleSuffix = ", Economy",
                    onPeopleChanged = onPeopleChanged
                )
            }

            item(key = null) {
                Spacer(modifier = Modifier.size(16.dp))
            }

            item(key = null) {
                ChildrenUserInput(
                    titleSuffix = ", Economy",
                    onPeopleChanged = onChildrenChanged
                )
            }

            item(key = null) {
                Spacer(modifier = Modifier.size(16.dp))
            }

            item(key = null) {
                DatesUserInput(
                    datesSelected,
                    onDateSelectionClicked = onDateSelectionClicked
                )
            }

            item(key = null) {
                Spacer(modifier = Modifier.size(16.dp))
            }

            item(key = null) {
                RoomsInput(
                    titleSuffix = ", Economy",
                    onPeopleChanged = onRoomsChanged
                )
            }

            item(key = null) {
                    InputItem(
                        textFieldValue = cardNumber,
                        label = "Enter Discount Code",
                        keyboardType = KeyboardType.Number,
                        onTextChanged = {  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                }

            item(key = null) {
                Spacer(modifier = Modifier.size(16.dp))
                }

            item(key = null) {
                    Text(text = "Payment Details", style = MaterialTheme.typography.subtitle1)
                }

            item(key = null) {
                Spacer(modifier = Modifier.size(16.dp))
                }

            item(key = null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "$350 x 5 Nights", style = MaterialTheme.typography.subtitle1, color = Color.Gray)
                    Text(text = "$1750", style = MaterialTheme.typography.subtitle1, color = Color.Gray)
                }
            }

            item(key = null) {
                Spacer(modifier = Modifier.size(16.dp))
            }

            item(key = null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Cleaning Fee", style = MaterialTheme.typography.subtitle1, color = Color.Gray)
                    Text(text = "$4", style = MaterialTheme.typography.subtitle1, color = Color.Gray)
                }
            }

            item(key = null) {
                Spacer(modifier = Modifier.size(16.dp))
            }

            item(key = null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "vat 10%", style = MaterialTheme.typography.subtitle1, color = Color.Gray)
                    Text(text = "$125", style = MaterialTheme.typography.subtitle1, color = Color.Gray)
                }
            }

            item(key = null) {
                Spacer(modifier = Modifier.size(16.dp))
            }

            item(key = null) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total (USD)", style = MaterialTheme.typography.subtitle1)
                    Text(text = "$1375", style = MaterialTheme.typography.subtitle1)
                }
            }

            item(key = null) {
                Spacer(modifier = Modifier.size(16.dp))
            }

            item(key = null) {
                Row(Modifier.fillMaxWidth()) {
                    Button(
                        onClick = onBook,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        if (viewState.isLoading) {
                            HorizontalDottedProgressBar()
                        }
                        if (!viewState.isLoading && viewState.bookingResponse != null) {
                                navigateToBack.invoke()
                        }
                        else {
                            Text(
                                text = "BOOK",
                                modifier = Modifier.padding(horizontal = 30.dp, vertical = 8.dp)
                            )
                        }

                    }
                }
            }

            }
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


@RequiresApi(Build.VERSION_CODES.O)
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
    BookNowScreen(
        viewModel = viewModel(),
        navigateToLogin= {},
        navigateToCalender= {},
        navigateToBack= {}
    )
}
