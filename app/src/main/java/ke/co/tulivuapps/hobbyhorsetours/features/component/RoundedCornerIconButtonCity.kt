package ke.co.tulivuapps.hobbyhorsetours.features.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto


@Composable
fun RoundedCornerIconButtonCity(icon: CityDto, navigateToSearchCity: (CityDto?) -> Unit) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = icon.url).apply(block = fun ImageRequest.Builder.() {
            crossfade(false)
            size(Size(1200, 900))
            error(R.drawable.coffee)
            fallback(R.drawable.coffee)
        }).build()
    )

    //animate slide up
    val animatedProgress = remember { Animatable(initialValue = 300f) }
    val opacityProgress = remember { Animatable(initialValue = 0f) }
    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = 0f,
            animationSpec = tween(300, easing = LinearEasing)
        )
        opacityProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(600)
        )
    }

    val animatedModifier = Modifier
        .graphicsLayer(translationY = animatedProgress.value)
        .alpha(opacityProgress.value)

    Box(
        modifier = animatedModifier
            .background(color = Color(R.color.light_gray),
                shape = RoundedCornerShape(10.dp))
    ) {
        IconButton(
            onClick = { navigateToSearchCity.invoke(icon)},
            modifier = Modifier
                .align(Alignment.Center)

        ) {
            Row(modifier = Modifier.align(Alignment.Center).width(130.dp).height(100.dp)) {
                Column(modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                    val imageModifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
                        .clip(shape = MaterialTheme.shapes.medium)
                    Image(
                        painter = painter,
                        modifier = imageModifier,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(modifier=Modifier.padding(2.dp),
                        text = icon.cityName,
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