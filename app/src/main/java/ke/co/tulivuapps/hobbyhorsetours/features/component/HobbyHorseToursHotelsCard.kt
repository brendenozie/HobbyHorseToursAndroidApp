package ke.co.tulivuapps.hobbyhorsetours.features.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.data.model.Status
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.HotelDto

/**
 * Created by brendenozie on 13.03.2023
 */

@Composable
fun HobbyHorseToursHotelsCard(
    modifier: Modifier = Modifier,
    status: Status,
    dto: HotelDto?,
    detailClick: () -> Unit,
    onTriggerEvent: (HotelDto) -> Unit
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(5.dp)
            .width(250.dp)
            .height(180.dp)
            .clickable { detailClick() },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            item(key = null) {
                if (dto != null) {
                    HobbyHorseToursNetworkImage(
                        imageURL = if (dto.img?.isNotEmpty() == true) dto.img[0].url else R.drawable.ic_place_holder,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .clip(shape = RoundedCornerShape(12)),
                        placeholder = R.drawable.ic_place_holder,
                        contentScale = ContentScale.FillBounds,
                    )
                }
            }
            item(key = null) {
                Spacer(modifier = Modifier.width(2.dp))
            }
            item(key = null) {
                Row(modifier = Modifier.padding(top = 5.dp, end = 5.dp, start = 5.dp)) {
                    Column(modifier = Modifier.weight(1f).align(Alignment.CenterVertically)) {
                        Text(
                            text = dto?.title ?: "",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 14.sp,
                            )
                        )
                        Text(
                            text = "Ksh ${dto?.price}" ?: "",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 14.sp,
                            )
                        )
                    }
                    Box(
                        modifier = Modifier.fillMaxHeight(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        if (dto != null) {
                            HobbyHorseToursFavoriteButton(
                                dto = dto,
                                onTriggerEvent = { dto ->
                                    onTriggerEvent.invoke(dto)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun BodyPreview() {
    HobbyHorseToursHotelsCard(
        status = Status.Alive,
        detailClick = {},
        onTriggerEvent = {},
        dto = HotelDto(
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
            travelStyleId = "",
            isFavorite= true,
        ),
    )
}