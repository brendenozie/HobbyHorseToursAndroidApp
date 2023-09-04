package ke.co.tulivuapps.hobbyhorsetours.composebase.features.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.Status
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.DestinationDto
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.ui.theme.Gray500
import ke.co.tulivuapps.hobbyhorsetours.composebase.utils.Utility.getAnimateFloat
import kotlinx.coroutines.delay

/**
 * Created by brendenozie on 27.03.2023
 */
@Composable
fun HobbyHorseToursDestinationFavoriteButton(
    dto: DestinationDto,
    onTriggerEvent: (DestinationDto) -> Unit
) {
    var isFavorite by rememberSaveable(dto) { mutableStateOf(dto.isFavorite) }
    var isClick by remember { mutableStateOf(false) }

    LaunchedEffect(isClick) {
        if (isClick) {
            delay(800)
            isClick = false
        }
    }

    IconButton(onClick = {
        isClick = true
        isFavorite = !isFavorite
        dto.isFavorite = isFavorite

//        if (viewModel is DestinationsViewModel) {
//            viewModel.onTriggerEvent(DestinationsViewEvent.UpdateFavorite(dto))
//        } else if (viewModel is SearchViewModel) {
//            viewModel.onTriggerEvent(SearchViewEvent.UpdateFavorite(dto))
//        }

        onTriggerEvent.invoke(dto)
    }) {
        val tintColor = if (isFavorite) Red else Gray500

        Icon(
            modifier = Modifier.size(if (isClick) getAnimateFloat().value.dp else 24.dp),
            painter = rememberVectorPainter(Icons.Default.Favorite),
            contentDescription = null,
            tint = tintColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    HobbyHorseToursDestinationFavoriteButton(
        dto = DestinationDto(
            localId= 1,
            id= "KBHKOLOD#4RE",
            title= "TEST TILE",
            description= "TEST DESCRIPTION FOR SCALE AND UNIT TEST",
            star= "5",
            lat= "-1.456789",
            location= "-1.456789",
            lang= "-1.456789",
            price= "15000",
            offer= "15000",
            offerPrice= "15000",
            userEmail= "testemail@gmail.com",
            cityId= "1",
            createdAt= "12.05pm 12/06/23",
            img= "random image",
            isFavorite= true,
        ),
        onTriggerEvent = {}
    )
}
