package ke.co.tulivuapps.hobbyhorsetours.features.screen.onboarding

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ke.co.tulivuapps.hobbyhorsetours.R
import ke.co.tulivuapps.hobbyhorsetours.features.carousel.Pager
import ke.co.tulivuapps.hobbyhorsetours.features.carousel.PagerState
import ke.co.tulivuapps.hobbyhorsetours.features.screen.home.navigation.homeNavigationRoute
import kotlinx.coroutines.delay

@Composable
fun OnBoardingScreen(onSkip: () -> Unit,
                     splashScreenViewModel: OnBoardingViewModel = hiltViewModel(),
                     navController: NavController
                    ) {

    val onboarding by splashScreenViewModel.onBoardingCompleted.collectAsState()

    LaunchedEffect(key1 = true) {
        delay(300L)
        if (onboarding) {
            navController.popBackStack()
            navController.navigate(homeNavigationRoute)
        }
//        else {
//            navController.navigate(loginNavigationRoute)
//        }
    }

    val currContext = LocalContext.current.applicationContext

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
                    .clickable(onClick = {
                                            splashScreenViewModel.setOnboarding()
                                            navController.popBackStack()
                                            onSkip() })
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
                    if (pagerState.currentPage != onboardingList.size - 1) {
                        pagerState.currentPage =
                            pagerState.currentPage + 1
                    }else{
                        splashScreenViewModel.setOnboarding()
                        navController.popBackStack()
                        onSkip()
                    }
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

data class Onboard(val title: String, val description: String, val lottieFile: Int)

val onboardingList = listOf(
    Onboard(
        "Explore a New World",
        "Find a place for travel,campaign,hiking.\nRelax and enjoy your trip",
        R.raw.profile
    ),
    Onboard(
        "Explore a New World",
        "Find a place for travel,campaign,hiking.\nRelax and enjoy your trip",
        R.raw.working
    ),
    Onboard(
        "Explore a New World",
        "Find a place for travel,campaign,hiking.\nRelax and enjoy your trip",
        R.raw.food
    )
)

