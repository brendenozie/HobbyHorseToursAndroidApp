package ke.co.tulivuapps.hobbyhorsetours.composebase.features.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,
        elevation = 8.dp,
        modifier = Modifier
            .padding(10.dp)
            .width(180.dp)
            .clickable { detailClick() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {

            HobbyHorseToursNetworkImage(
                    imageURL = R.drawable.ic_place_holder,//dto?.img,
                    modifier = Modifier
                        .fillMaxHeight()
//                        .width(80.dp)
                        .size(140.dp)
                        .clip(shape = RoundedCornerShape(15)),
                    placeholder = R.drawable.ic_place_holder,
                    contentScale = ContentScale.Crop,
                )
                Spacer(modifier = Modifier.width(10.dp))
            Row(modifier = Modifier.padding(top = 20.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = dto?.title.orEmpty(),
                        style = TextStyle(
                            color = Color.LightGray,
                            fontSize = 16.sp,
                        )
                    )
                    Text(
                        text = dto?.price.orEmpty(),
                        style = TextStyle(
                            color = Color.LightGray,
                            fontSize = 16.sp,
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Icon(
                        Icons.Default.Add,
                        tint = Color.White,
                        modifier = Modifier.padding(10.dp),
                        contentDescription = "flower_card_icon"
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
            img= null,
            isFavorite= true,
        ),
    )
}