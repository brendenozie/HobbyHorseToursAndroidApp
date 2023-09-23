package ke.co.tulivuapps.hobbyhorsetours.features.lottie

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import ke.co.tulivuapps.hobbyhorsetours.R

@Composable
fun LottieWorkingLoadingView(context: Context) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.working))
    LottieAnimation(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        restartOnPlay = true,
        alignment = Alignment.Center,
        iterations = LottieConstants.IterateForever,
        composition = composition
    )
}
