package ke.co.tulivuapps.hobbyhorsetours.features.lottie

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import ke.co.tulivuapps.hobbyhorsetours.R


@Composable
fun LottieWorkingLoadingView() {

    val composition by rememberLottieComposition(
                            spec = LottieCompositionSpec.RawRes(R.raw.working),
                        )

    /** to control the animation speed */
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        speed = 1f,
        restartOnPlay = false,

        )

    LottieAnimation(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth(),
        progress = progress,
        composition = composition,
        contentScale = ContentScale.Crop,

        )
//    LottieAnimation(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(250.dp),
//        restartOnPlay = true,
//        alignment = Alignment.Center,
//        iterations = LottieConstants.IterateForever,
//        composition = composition
//    )
}
