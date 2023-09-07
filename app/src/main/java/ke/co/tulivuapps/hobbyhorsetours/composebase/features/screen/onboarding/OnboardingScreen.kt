package ke.co.tulivuapps.hobbyhorsetours.composebase.features.screen.onboarding

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.guru.composecookbook.onboarding.OnboardingPagerSlide
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.carousel.Pager
import ke.co.tulivuapps.hobbyhorsetours.composebase.features.carousel.PagerState

@Composable
fun OnBoardingScreen(onSkip: () -> Unit) {
    val pagerState: PagerState = run {
        remember {
            PagerState(0, 0, onboardingList.size - 1)
        }
    }
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Pager(
                state = pagerState,
                orientation = Orientation.Horizontal,
                modifier = Modifier.fillMaxSize()
            ) {
                OnboardingPagerItem(onboardingList[commingPage])
            }
            Text(
                text = "Skip",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(vertical = 48.dp, horizontal = 16.dp)
                    .clickable(onClick = onSkip)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 120.dp)
            ) {
                onboardingList.forEachIndexed { index, _ ->
                    OnboardingPagerSlide(
                        selected = index == pagerState.currentPage,
                        MaterialTheme.colors.primary,
                        Icons.Filled.PlayArrow
                    )
                }
            }
            Button(
                onClick = {
                    if (pagerState.currentPage != onboardingList.size - 1) pagerState.currentPage =
                        pagerState.currentPage + 1
                },
                modifier = Modifier
                    .animateContentSize()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 32.dp)
                    .height(50.dp)
                    .clip(CircleShape)
            ) {
                Text(
                    text = if (pagerState.currentPage == onboardingList.size - 1) "Let's Begin" else "Next",
                    modifier = Modifier.padding(horizontal = 32.dp),
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}

data class Onboard(val title: String, val description: String, val lottieFile: String)

val onboardingList = listOf(
    Onboard(
        "Team Collaborations",
        "Our tools help your teams collaborate for the best output results",
        "profile.json"
    ),
    Onboard(
        "Improve Productivity",
        "Our tools are designed to improve productivity by automating all the stuff for you",
        "working.json"
    ),
    Onboard(
        "Growth Tracking",
        "We provide dashboard and charts to track your growth easily and suggestions.",
        "food.json"
    )
)

