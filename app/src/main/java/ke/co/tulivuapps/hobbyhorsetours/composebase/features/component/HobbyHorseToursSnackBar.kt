package ke.co.tulivuapps.hobbyhorsetours.composebase.features.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by brendenozie on 12.03.2023
 */

@Composable
fun HobbyHorseToursSnackBar(
    snackbarHostState: SnackbarHostState,
    snackBarEnum: SnackBarEnum
) {
    SnackbarHost(snackbarHostState) { data ->
        Snackbar(
            elevation = 0.dp,
            backgroundColor = Color(integerResource(id = snackBarEnum.backgroundColor)),
            snackbarData = data,
            shape = MaterialTheme.shapes.medium
        )
    }
}

sealed class SnackBarEnum(val backgroundColor: Int) {
    //TODO("Add your own SnackBarEnum")
    object SUCCESS : SnackBarEnum(ke.co.tulivuapps.hobbyhorsetours.composebase.R.color.black)
    object ERROR : SnackBarEnum(ke.co.tulivuapps.hobbyhorsetours.composebase.R.color.black)
}

@Preview
@Composable
private fun BodyPreview() {
    HobbyHorseToursSnackBar(
        rememberScaffoldState().snackbarHostState,
        SnackBarEnum.SUCCESS
    )
}