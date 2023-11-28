package ke.co.tulivuapps.hobbyhorsetours.features.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ke.co.tulivuapps.hobbyhorsetours.R


@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun SlidingBanner(visibility: MutableTransitionState<Boolean> = MutableTransitionState(false)) {

    val animateStateVisibility = remember { MutableTransitionState(false) }

    animateStateVisibility.apply { targetState = true }

    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        // Collect from the pager state a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
//            AnalyticsService.sendPageSelectedEvent(page)
        }
    }

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = R.drawable.ic_sale_banner)
            .apply(block = fun ImageRequest.Builder.() {
                crossfade(false)
                size(Size(1200, 900))
                error(R.drawable.coffee)
                fallback(R.drawable.coffee)
            }).build()
    )

    AnimatedVisibility(
        animateStateVisibility,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            itemSpacing = 5.dp,
        ) {
            Image(
                modifier = Modifier
                        .animateEnterExit(
                        enter = slideIn(
                            tween(
                                delayMillis = 300,
                                easing = LinearOutSlowInEasing,
                                durationMillis = 800
                            )
                        ) { IntOffset(0, -120) },
            )
                    .padding(horizontal = 4.dp)
                    .fillMaxWidth(),
                painter = painter,
                contentScale = ContentScale.FillWidth,
                contentDescription = "sliding_banner_image"
            )
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSlidingBanner() {
    SlidingBanner()
}