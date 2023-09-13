package ke.co.tulivuapps.hobbyhorsetours.features.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.TravelStyleDto


@Composable
fun RoundedCornerIconButtonTravelStyle(modifier: Modifier, icon: TravelStyleDto?) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = icon?.url).apply(block = fun ImageRequest.Builder.() {
            crossfade(false)
            size(Size(1200,900))
            error(R.drawable.coffee)
            fallback(R.drawable.coffee)
        }).build()
    )
    Box(
        modifier = modifier
            .background(color = Color(R.color.light_gray),
                shape = RoundedCornerShape(10.dp))
    ) {
        IconButton(
            onClick = { },
            modifier = Modifier
                .align(Alignment.Center)
                .padding(2.dp)
        ) {
            Row(modifier = Modifier.align(Alignment.Center).width(130.dp).height(100.dp)) {
                Column(modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    Image(
                        painter = painter,
                        contentDescription = "rounded_corner_icon_button"
                    )
                    if (icon != null) {
                        Text(
                            text = icon.styleName!!,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 12.sp,
                            )
                        )
                    }
                }
            }
        }
    }
}