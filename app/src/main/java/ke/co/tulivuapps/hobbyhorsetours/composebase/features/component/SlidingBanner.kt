package ke.co.tulivuapps.hobbyhorsetours.composebase.features.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import ke.co.tulivuapps.hobbyhorsetours.composebase.R


@OptIn(ExperimentalPagerApi::class)
@Composable
fun SlidingBanner() {
    val pagerState = rememberPagerState()

    LaunchedEffect(pagerState) {
        // Collect from the pager state a snapshotFlow reading the currentPage
        snapshotFlow { pagerState.currentPage }.collect { page ->
//            AnalyticsService.sendPageSelectedEvent(page)
        }
    }

    HorizontalPager(
        count = 3,
        state = pagerState,
        itemSpacing = 5.dp,
    ) { page ->
        Image(
            modifier = Modifier
                .fillMaxWidth(),
            bitmap = ImageBitmap.imageResource(id = R.drawable.ic_sale_banner),
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


@Preview(showBackground = true)
@Composable
fun PreviewSlidingBanner() {
    SlidingBanner()
}