package ke.co.tulivuapps.hobbyhorsetours.features.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ke.co.tulivuapps.hobbyhorsetours.R

@Composable
fun ProfilePicture(
    image: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: Dp = ProfileSizes.medium,
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = image).apply(block = fun ImageRequest.Builder.() {
            crossfade(false)
            error(R.drawable.coffee)
            fallback(R.drawable.coffee)
        }).build()
    )

    Image(
        painter = painter,
        modifier = modifier
            .size(size)
            .clip(CircleShape),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop
    )
}

object ProfileSizes {
    val small = 20.dp
    val medium = 32.dp
    val large = 64.dp
}

//@Preview
//@Composable
//fun ProfilePicturePreview() {
//    Column {
//        ProfilePicture(
//            imageId = DemoDataProvider.tweetList.first().authorImageId,
//            contentDescription = "Profile picture",
//            size = ProfileSizes.small
//        )
//        ProfilePicture(
//            imageId = DemoDataProvider.tweetList.first().authorImageId,
//            contentDescription = "Profile picture",
//            size = ProfileSizes.medium
//        )
//    }
//}
