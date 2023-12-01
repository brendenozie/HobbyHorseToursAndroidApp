package ke.co.tulivuapps.hobbyhorsetours.features.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.features.ui.theme.Dimension
import ke.co.tulivuapps.hobbyhorsetours.features.ui.theme.hobbyTypography


@OptIn(ExperimentalPagerApi::class, ExperimentalAnimationApi::class)
@Composable
fun SlidingBanner(visibility: MutableTransitionState<Boolean> = MutableTransitionState(false)) {


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
        visibility,
        enter = fadeIn(
            tween(
                delayMillis = 600,
                easing = LinearOutSlowInEasing,
                durationMillis = 500
                )
            )+scaleIn(
                tween(
                    delayMillis = 600,
                    easing = LinearOutSlowInEasing,
                    durationMillis = 500
            )
        ),
        exit = ExitTransition.None
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            itemSpacing = 5.dp,
        ) {
            Box(modifier = Modifier
//                .background(MaterialTheme.colors.secondary.copy5(alpha = 0.72f))
                .fillMaxWidth()
                .height(190.dp)) {
                Image(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .fillMaxWidth(),
                    painter = painter,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = "sliding_banner_image"
                )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 4.dp)) {
                    Column(
                        modifier = Modifier
//                        .background(MaterialTheme.colors.surface.copy(alpha = 0.22f))
                            .padding(
                                top = Dimension.lg,
                                start = Dimension.md,
                                bottom = Dimension.md,
                                end = Dimension.md
                            )
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .align(Alignment.BottomStart)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .animateEnterExit(
                                    enter = slideInHorizontally(
                                        tween(
                                            delayMillis = 900,
                                            easing = LinearOutSlowInEasing,
                                            durationMillis = 800
                                        )
                                    )
                                ),
                            text = "Explore the world and\nfind the best deal",
                            style = hobbyTypography.h5,
                            color = colorResource(id = R.color.black)
                        )
                        Spacer(modifier = Modifier.height(Dimension.xs))
                        Text(
                            modifier = Modifier
                                .animateEnterExit(
                                    enter = fadeIn(
                                        tween(
                                            delayMillis = 1000,
                                            easing = LinearOutSlowInEasing,
                                            durationMillis = 800
                                        )
                                    )
                                ),
                            text = "special offer",
                            style = hobbyTypography.subtitle2,
                            color = colorResource(id = R.color.primary)
                        )
                    }
                }
                HorizontalPagerIndicator(
                    pagerState = pagerState,
                    modifier = Modifier
                        .padding(16.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSlidingBanner() {
    SlidingBanner()
}