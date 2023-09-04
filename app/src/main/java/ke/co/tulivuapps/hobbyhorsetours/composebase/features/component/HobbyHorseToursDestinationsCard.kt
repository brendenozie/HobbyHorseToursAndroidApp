package ke.co.tulivuapps.hobbyhorsetours.composebase.features.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ke.co.tulivuapps.hobbyhorsetours.composebase.R
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.Status
import ke.co.tulivuapps.hobbyhorsetours.composebase.data.model.dto.DestinationDto

/**
 * Created by brendenozie on 13.03.2023
 */

@Composable
fun HobbyHorseToursDestinationsCard(
    modifier: Modifier = Modifier,
    status: Status,
    dto: DestinationDto?,
    detailClick: () -> Unit,
    onTriggerEvent: (DestinationDto) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { detailClick() },
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier
                .size(100.dp)
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
            ) {
                HobbyHorseToursNetworkImage(
                    imageURL = R.drawable.ic_place_holder,//dto?.img,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(80.dp)
                        .clip(shape = RoundedCornerShape(15)),
                    placeholder = R.drawable.ic_place_holder,
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.fillMaxHeight()) {
                    HobbyHorseToursText(
                        text = dto?.title.orEmpty(),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                    HobbyHorseToursText(
                        text = dto?.title.orEmpty(),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondaryVariant,
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Card(
                            modifier = Modifier
                                .size(12.dp),
                            backgroundColor = Color.Red,//if (status == Status.Alive) Color.Green else Color.Red,
                            shape = RoundedCornerShape(50)
                        ) {}
                        Spacer(modifier = Modifier.width(5.dp))
                        HobbyHorseToursText(
                            text = status.name,
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.secondaryVariant,
                        )
                    }
                }
            }
            Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.CenterEnd) {
                dto?.let {
                    HobbyHorseToursDestinationFavoriteButton(
                        dto = it,
                        onTriggerEvent = { dto ->
                            onTriggerEvent.invoke(dto)
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun BodyPreview() {
    HobbyHorseToursDestinationsCard(
        status = Status.Alive,
        detailClick = {},
        onTriggerEvent = {},
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
    )
}