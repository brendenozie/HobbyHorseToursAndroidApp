package ke.co.tulivuapps.hobbyhorsetours.features.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ke.co.tulivuapps.hobbyhorsetours.data.model.dto.CityDto

@Composable
fun CitiesItem(
    item: CityDto,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.caption,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val borderColor = SolidColor(Color.LightGray)

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

    Column(
        modifier = animatedModifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfilePicture(
            image = item.url,
            contentDescription = null,
            size = ProfileSizes.large,
            modifier = Modifier
                .border(
                    shape = CircleShape,
                    border = BorderStroke(width = 3.dp, brush = borderColor)
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = rememberRipple(bounded = false, radius = ProfileSizes.large / 2),
                    enabled = true,
                    onClickLabel = null,
                    onClick = onClick
                )
        )
        Text(text = item.cityName, style = textStyle, textAlign = TextAlign.Center)
    }
}

//@Preview
//@Composable
//fun StoryItemPreview() {
//    StoryItem(
//        profileImageId = DemoDataProvider.tweet.authorImageId,
//        profileName = DemoDataProvider.tweet.author,
//        isMe = false,
//        onClick = {}
//    )
//}
//
//@Preview
//@Composable
//fun StoryItemMePreview() {
//    StoryItem(
//        profileImageId = DemoDataProvider.tweet.authorImageId,
//        profileName = DemoDataProvider.tweet.author,
//        isMe = true,
//        onClick = {}
//    )
//}
