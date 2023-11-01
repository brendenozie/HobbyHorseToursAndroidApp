package ke.co.tulivuapps.hobbyhorsetours.features.screen.profile

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ke.co.tulivuapps.hobbyhorsetours.R

@Composable
fun TopScrollingContent(scrollState: ScrollState, username:String, email:String,img:String) {
    val visibilityChangeFloat = scrollState.value > initialImageFloat - 20
    Row {
        AnimatedImage(scroll = scrollState.value.toFloat(),img)
        Column(
            modifier = Modifier
                .padding(start = 8.dp, top = 48.dp)
                .alpha(animateFloatAsState(if (visibilityChangeFloat) 0f else 1f, label = "").value)
        ) {
            Text(
                text = username,
//                style = typography.headlineSmall.copy(fontSize = 18.sp),
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = email,
//                style = materialTypography.labelMedium
                style = typography.subtitle2
            )
        }
    }
}

@Composable
fun AnimatedImage(scroll: Float,img: String) {
    val dynamicAnimationSizeValue = (initialImageFloat - scroll).coerceIn(36f, initialImageFloat)
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = img).apply(block = fun ImageRequest.Builder.() {
            crossfade(false)
//            size(Size(1200, 900))
            error(R.drawable.coffee)
            fallback(R.drawable.coffee)
        }).build()
    )

    Image(
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .padding(start = 16.dp)
            .size(animateDpAsState(Dp(dynamicAnimationSizeValue), label = "").value)
            .clip(CircleShape)
    )
}
