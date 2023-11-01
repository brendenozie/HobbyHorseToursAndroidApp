@file:OptIn(ExperimentalAnimationApi::class)

package ke.co.tulivuapps.hobbyhorsetours.features.screen.onboarding.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import com.google.accompanist.navigation.animation.composable
import ke.co.tulivuapps.hobbyhorsetours.features.screen.homee.navigation.navigateToHomee
import ke.co.tulivuapps.hobbyhorsetours.features.screen.onboarding.OnBoardingScreen

/**
 * Created by brendenozie on 23.01.2023
 */

const val onboardingNavigationRoute = "onboarding_route"

fun NavController.navigateToOnboarding(
    navOptions: NavOptions? = null
) {
    this.navigate(onboardingNavigationRoute, navOptions)
}


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.onbooardingScreen(navController: NavHostController) {
    composable(onboardingNavigationRoute) {
        OnBoardingScreen(onSkip = {navController.navigateToHomee()},navController = navController)
    }
}